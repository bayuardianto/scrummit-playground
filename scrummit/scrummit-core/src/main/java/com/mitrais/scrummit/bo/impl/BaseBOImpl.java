package com.mitrais.scrummit.bo.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.mitrais.scrummit.config.ApplicationConfig;
import com.mitrais.scrummit.config.SMConstant;
import com.mitrais.scrummit.model.User;

public class BaseBOImpl {
    protected HttpServletRequest request;

    public HttpServletRequest getRequest() {
        request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    public User getLoggedInUser() {
        User user = (User) this.getRequest().getSession().getAttribute("CURRENT_USER");
        return user;
    }

    public void resolveTenant() {
        ApplicationConfig.switchDbName(getLoggedInUser().getAssocOrgId().getDbSettings().getDbName());
    }

    public void resolveCentral() {
        ApplicationConfig.switchDbName(SMConstant.SCRUMMIT_DB_CENTRAL_NAME);
    }

}
