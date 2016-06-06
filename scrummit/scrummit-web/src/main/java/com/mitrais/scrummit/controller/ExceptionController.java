package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ExceptionController {

	@RequestMapping(value = "/401", method = RequestMethod.GET)
	public String index(Map<String, Object> model) {
		return "views/exceptions/401";
	}
}
