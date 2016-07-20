package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@RequestMapping(value = "/views/menufirst", method = RequestMethod.GET)
	public String projectViews(Map<String, Object> model) {
		
		return "views/menu";
	}
}
