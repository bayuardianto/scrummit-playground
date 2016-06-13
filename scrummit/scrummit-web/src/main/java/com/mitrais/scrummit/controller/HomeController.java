package com.mitrais.scrummit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mitrais.scrummit.dao.UserDAO;
import com.mitrais.scrummit.model.User;

@Controller
public class HomeController {

	@Autowired
	UserDAO userDao;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {

		model.put("title", "Hello");
		model.put("msg", "Welcome");
		
		return "index";
	}
	
	@RequestMapping(value = "/views/dashboard", method = RequestMethod.GET)
	public String main(Map<String, Object> model) {
		
		return "views/dashboard";
	}
	
	@RequestMapping(value = "/views/content", method = RequestMethod.GET)
	public String content(Map<String, Object> model) {
		
		return "views/common/content";
	}
	
	@RequestMapping(value = "/views/navigation", method = RequestMethod.GET)
	public String navigation(Map<String, Object> model, HttpServletRequest request) {
				
		return "views/common/navigation";
	}
	
	@RequestMapping(value = "/views/topnavbar", method = RequestMethod.GET)
	public String topnavbar(Map<String, Object> model) {
		
		return "views/common/topnavbar";
	}
	
	@RequestMapping(value = "/views/footer", method = RequestMethod.GET)
	public String footer(Map<String, Object> model) {
		
		return "views/common/footer";
	}

    @RequestMapping(value = "/views/verified", method = RequestMethod.GET)
    public String verified(Map<String, Object> model) {

        return "views/verified";
    }

	@RequestMapping(value = "views/notif_modal", method = RequestMethod.GET)
	public String notifModal(Map<String, Object> model) {
		return "views/common/notif_modal";
	}
}
