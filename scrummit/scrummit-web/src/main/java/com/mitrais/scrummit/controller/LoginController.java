package com.mitrais.scrummit.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mitrais.scrummit.bo.UserBO;
import com.mitrais.scrummit.model.User;

@Controller
public class LoginController {
	
	@Autowired
    UserBO                userBO;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

	@RequestMapping(value = "/views/login", method = RequestMethod.GET)
	public String login(Map<String, Object> model) {
		
		return "views/login";
	}
	
	@RequestMapping(value = "/views/register", method = RequestMethod.GET)
	public String register(Map<String, Object> model) {
		
		return "views/register";
	}
	
	@RequestMapping(value = "/login/authenticate/", method = {RequestMethod.POST, RequestMethod.GET})
	public @ResponseBody Map<String, String> authenticate(@RequestBody User rqUser, HttpServletRequest req) {
		
        User user = userBO.findByUsername(rqUser.getUsername());
		
		Map<String, String> rs = new HashMap<>();
		
        if (user != null && passwordEncoder.matches(rqUser.getPassword(), user.getPassword())) {
			req.getSession().setAttribute("CURRENT_USER", user);
			req.getSession().setAttribute("CURRENT_ORG", user.getAssocOrgId());
			if(user.getIsActivated())
			{
				rs.put("success", "1");
			}
			else
			{
				rs.put("success", "0");
				rs.put("message", "This account has not been activated.");
			}
		} else {
			rs.put("success", "0");
			rs.put("message", "Invalid Username or Password");
		}
		
		return rs;
	}
	
	@RequestMapping(value = "/logout/", method = RequestMethod.POST)
	public @ResponseBody Boolean logout(HttpServletRequest req) {
		req.getSession().removeAttribute("CURRENT_USER");
		req.getSession().removeAttribute("CURRENT_ORG");
		return true;
	}
}
