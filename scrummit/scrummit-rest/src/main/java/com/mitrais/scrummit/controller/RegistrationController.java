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

import com.mitrais.scrummit.bo.RegistrationBO;
import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.model.User;
import com.mitrais.scrummit.util.SmtpMailSender;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationBO registrationBO;

    @Autowired
    private UserBO         userBO;

    @Autowired
    BCryptPasswordEncoder  encoder;



    @Autowired
    private SmtpMailSender mailSender;

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user, HttpServletRequest request) throws MailException, MessagingException {
        User savedUser = registrationBO.RegisterUserComplete(user);
        
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
}
