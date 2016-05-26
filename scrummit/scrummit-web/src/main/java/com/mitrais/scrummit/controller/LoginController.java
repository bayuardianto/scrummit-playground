package com.mitrais.scrummit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.User;

@Controller
public class LoginController {
	
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/views/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		
		return "views/login";
	}
	
	@RequestMapping(value = "/views/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model) {
		
		return "views/register";
	}
	
	@RequestMapping(value = "/login/authenticate/", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, String> authenticate(@RequestBody User rqUser) {
		
		//TODO: Define UserService and use instead of UserDAO
		User user = userDAO.findByUsername(rqUser.getUsername());
		
		Map<String, String> rs = new HashMap<>();
		
		//TODO: replace the logic with BCryptPasswordEncoder match() function for validating passwords
		if (user != null && user.getPassword().equals(rqUser.getPassword())) {
			rs.put("success", "1");
		} else {
			rs.put("success", "0");
			rs.put("message", "Invalid Username or Password");
		}
		
		return rs;
	}
}
