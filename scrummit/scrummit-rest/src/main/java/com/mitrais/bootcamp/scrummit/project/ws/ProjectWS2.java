package com.mitrais.bootcamp.scrummit.project.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/card")
public class ProjectWS2 {

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
