package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IterationController {

	@RequestMapping(value = "/views/iteration/modal", method = RequestMethod.GET)
	public String showIterationCreateModal(Map<String, Object> model) {
		
		return "views/iteration/modal";
		
	}
	
}
