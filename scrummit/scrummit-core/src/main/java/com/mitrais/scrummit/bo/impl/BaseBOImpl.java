package com.mitrais.scrummit.bo.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mitrais.scrummit.bo.BaseBO;
import com.mitrais.scrummit.config.ApplicationConfig;
import com.mitrais.scrummit.config.SMConstant;
import com.mitrais.scrummit.dao.CommonDAO;
import com.mitrais.scrummit.model.Common;
import com.mitrais.scrummit.model.User;
import com.mitrais.scrummit.multitenancy.MultiTenantMongoDbFactory;

@SuppressWarnings("rawtypes")
public class BaseBOImpl<T extends Common, S extends CommonDAO> implements BaseBO<T> {
    protected HttpServletRequest request;
    public boolean               isCentralBO;

    @Autowired
    protected S                  currentDAO;

    public String                tenantName;

    /**
     * The default constructor for BaseBOImpl, isCentralBO default to false.
     * Database always set to tenant for this BO
     * 
     */
    public BaseBOImpl() {
        super();
        this.setCentralBO(false);
    }

    /**
     * The costructor for BaseBOImpl to set the BO database type
     * 
     * @param <boolean> set to true if a central database bo, false for tenant
     * 
     */
    public BaseBOImpl(boolean isCentralBO) {
        super();
        this.setCentralBO(isCentralBO);
    }

    public boolean isCentralBO() {
        return isCentralBO;
    }

    public void setCentralBO(boolean isCentralBO) {
        this.isCentralBO = isCentralBO;
    }

    public HttpServletRequest getRequest() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public User getLoggedInUser() {
        User user = (User) this.getRequest().getSession().getAttribute("CURRENT_USER");
        return user;
    }

    public void resolveTenant() {
        User user = getLoggedInUser();
        if (user != null) {
            ApplicationConfig.switchDbName(user.getAssocOrgId().getDbSettings().getDbName());
        } else if (tenantName != null) {
            ApplicationConfig.switchDbName(tenantName);
        } else {
            System.out.println("\n\nNo database_name\n\n");
            MultiTenantMongoDbFactory.clearDatabaseNameForCurrentThread();
        }
    }

    public void resolveCentral() {
        ApplicationConfig.switchDbName(SMConstant.SCRUMMIT_DB_CENTRAL_NAME);
    }

    /**
     * Default method to handle save in current BO. Automatically set modifiedBy
     * attribute value
     * 
     * @param <Common>
     *            set to true if a central database bo, false for tenant
     * @param <CommonDAO>
     *            the DAO used in this BO
     * 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public T save(T commonObject) {
        User user = getLoggedInUser();
        commonObject.setModifiedBy(user != null ? user.getId() : null);
        return (T) currentDAO.save(commonObject);
    }

    /**
     * Default method to handle insert in current BO. Automatically set
     * modifiedBy, createdBy, isDeleted attributes value
     * 
     * @param <Common>
     *            set to true if a central database bo, false for tenant
     * @param <CommonDAO>
     *            the DAO used in this BO
     * 
     */

    @SuppressWarnings("unchecked")
    public T insert(T commonObject) {
        User user = getLoggedInUser();
        commonObject.setModifiedBy(user != null ? user.getId() : null);
        commonObject.setCreatedBy(user != null ? user.getId() : null);
        commonObject.setIsDeleted(false);
        return (T) currentDAO.insert(commonObject);
    }

    /**
     * Default method to handle delete in current BO. Automatically set
     * modifiedBy, isDeleted attribute value
     * 
     * @param <Common>
     *            set to true if a central database bo, false for tenant
     * @param <CommonDAO>
     *            the DAO used in this BO
     * 
     */
    public T delete(T commonObject) {
        commonObject.setIsDeleted(true);
        return (T) save(commonObject);
    }

    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }

}
