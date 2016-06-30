package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class OrgMembersController {
	@RequestMapping(value = "/views/organizationmembers", method = RequestMethod.GET)
	public String userProfile(Map<String, Object> model) {
		
		return "/views/organizationmembers";
	}
	
	@RequestMapping(value = "/views/ormemberdetail", method = RequestMethod.GET)
	public String addMember(Map<String, Object> model) {
		return "views/ormember_detail";
	}
}
