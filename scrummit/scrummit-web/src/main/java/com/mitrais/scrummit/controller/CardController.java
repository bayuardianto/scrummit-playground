package com.mitrais.scrummit.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CardController {

	@RequestMapping(value = "/views/card_modal", method = RequestMethod.GET)
	public String cardModalView(Map<String, Object> model) {
		
		return "views/card/modal";
	}
	
}
