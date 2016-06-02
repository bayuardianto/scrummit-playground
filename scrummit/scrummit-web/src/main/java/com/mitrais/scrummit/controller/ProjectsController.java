package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProjectsController {
	
	@RequestMapping(value = "/views/projects", method = RequestMethod.GET)
	public String projectViews(Map<String, Object> model) {
		
		return "views/projects";
	}

	@RequestMapping(value = "/views/addproject", method = RequestMethod.GET)
	public String addProject(Map<String, Object> model) {

		return "views/addproject";
	}
}
