package com.mitrais.scrummit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.RegistrationBO;
import com.mitrais.scrummit.model.User;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    private RegistrationBO registrationBO;

    @RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User create(@RequestBody User user) {
        return registrationBO.RegisterUserComplete(user);
    }
}
