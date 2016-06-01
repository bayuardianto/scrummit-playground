package com.mitrais.scrummit.bo.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.RegistrationBO;
import com.mitrais.scrummit.config.SMConstant;
import com.mitrais.scrummit.dao.OrganizationDAO;
import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.Organization;
import com.mitrais.scrummit.model.User;
@Service
public class RegistrationBOImpl extends BaseBOImpl implements RegistrationBO {
    private static final Log log = LogFactory.getLog(RegistrationBOImpl.class);
    @Autowired
    UserDAO         userDAO;

    @Autowired
    OrganizationDAO organizationDAO;

    @Override
    public User RegisterUserComplete(User user) {
        log.info("registering user complete with username: " + user.getUsername());
        resolveCentral();
        User savedUser = null;
        if (null != user.getAssocOrgId()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            user.setIsActivated(false);
            user.setActivationKey(encoder.encode(user.getUsername() + user.getEmail()));

            // save org, save user, update ref,
            user.getAssocOrgId().initializeDbSettings(SMConstant.SCRUMMIT_DB_TENANT_PREFIX + user.getUsername(), "",
                    "",
                    "");
            Organization savedOrg = organizationDAO.insert(user.getAssocOrgId());

            // TODO: remove this line, automatically updated after save,
            user.setAssocOrgId(savedOrg);

            savedUser = userDAO.insert(user);
            // set org owner
            savedOrg.setOwner(user);
            organizationDAO.save(savedOrg);

        }
        return savedUser;
    }

}
