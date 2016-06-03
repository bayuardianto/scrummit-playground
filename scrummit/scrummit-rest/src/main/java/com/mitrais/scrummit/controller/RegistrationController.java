package com.mitrais.scrummit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.OrganizationBO;
import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.config.SMConstant;
import com.mitrais.scrummit.model.Organization;
import com.mitrais.scrummit.model.OrganizationMember;
import com.mitrais.scrummit.model.User;
import com.mitrais.scrummit.util.ScrummitUtil;
import com.mitrais.scrummit.util.SmtpMailSender;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    
    @Autowired
    private OrganizationBO organizationBO;

    @Autowired
    private OrganizationMemberBO organizationMemberBO;

    @Autowired
    private UserBO         userBO;

    @Autowired
    BCryptPasswordEncoder  encoder;



    @Autowired
    private SmtpMailSender mailSender;

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user, HttpServletRequest request) throws MailException, MessagingException {
        User savedUser = RegisterUserComplete(user);
        
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
                + request.getContextPath();

        mailSender.send(savedUser.getEmail(), "Scrummit Account verification",
                "click this link to activate your account\n " + path + "/#/verified?key="
                        + savedUser.getActivationKey(),
                "click this <a href=\"" + path + "/#/verified?key=" + savedUser.getActivationKey()
                        + "\">link</a> to activate your account <br>");

        return savedUser;
    }

    @RequestMapping(path = "/verify", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> verify(@RequestParam(name = "key") String key) {
        User user = userBO.findByActivationKey(key);
        Map<String, Object> response = new HashMap<String, Object>();
        if (user == null) {
            response.put("error", 1);
            response.put("message", "The provided key isn't valid");
            return response;
        } else {
            if (encoder.matches(user.getUsername() + user.getEmail(), key)) {
            userBO.activateUser(user.getUsername());
                response.put("error", 0);
                response.put("message", "success");
            } else {
                response.put("error", 1);
                response.put("message", "Key not match");
            }
            return response;
        }

    }

    private User RegisterUserComplete(User user) {

        User savedUser = null;
        if (null != user.getAssocOrgId()) {
            String tenantName = ScrummitUtil.generateTenantName(user);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String hashedPassword = encoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            user.setIsActivated(false);
            user.setActivationKey(encoder.encode(user.getUsername() + user.getEmail()));
            // save org, save user, update ref,
            user.getAssocOrgId().initializeDbSettings(tenantName, "",
                    "",
                    "");
            Organization savedOrg = organizationBO.insert(user.getAssocOrgId());

            // TODO: remove this line, automatically updated after save,
            user.setAssocOrgId(savedOrg);

            savedUser = userBO.insert(user);
            // set org owner
            savedOrg.setOwner(user);
            organizationBO.save(savedOrg);

            // To help tenantResolver find the correct tenantDB, where no user
            // login here, usually on registration only
            organizationMemberBO.setTenantName(tenantName);

            OrganizationMember member = new OrganizationMember();
            member.setAccessRights(SMConstant.ORGANIZATION_MEMMBER_ACCESS_RIGHT_OWNER);
            member.setFullName(user.getFirstName()+" "+user.getLastName());
            member.setIsActive(true);
            member.setUserId(user.getId());
            organizationMemberBO.insert(member);
        }
        return savedUser;

    }
}
