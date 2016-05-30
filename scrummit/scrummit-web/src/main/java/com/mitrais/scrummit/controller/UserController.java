package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@RequestMapping(value = "/user/profile", method = RequestMethod.GET)
	public String userProfile(Map<String, Object> model) {
		
		return "views/user/profile";
	}
	
	@RequestMapping(value = "/user/password", method = RequestMethod.GET)
	public String managePassword(Map<String, Object> model) {
		
		return "views/user/password";
	}
}
