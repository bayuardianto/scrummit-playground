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
	
	@RequestMapping(value = "/views/project/board", method = {RequestMethod.GET, RequestMethod.POST})
	public String projectBoard(Map<String, Object> model) {
		
		return "views/project/board";
	}
	
	@RequestMapping(value = "/views/addproject", method = RequestMethod.GET)
	public String addProject(Map<String, Object> model) {

		return "views/addproject";
	}

	@RequestMapping(value = "/views/updateproject", method = RequestMethod.GET)
	public String updateProject(Map<String, Object> model) {

		return "views/addproject";
	}
	
	@RequestMapping(value = "views/project/iteration/bar", method = RequestMethod.GET)
	public String iterationBarViews(Map<String, Object> model) {
		
		return "views/project/iteration_bar";
	}
}
