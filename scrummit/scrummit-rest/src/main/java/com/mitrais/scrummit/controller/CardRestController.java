package com.mitrais.scrummit.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardRestController {

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public @ResponseBody String list() {
        return "list all card";
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public @ResponseBody String getCard(
            @RequestParam(name = "id", required = false, defaultValue = "defaultValue") String id) {
        return "get card with id " + id;
    }
}
