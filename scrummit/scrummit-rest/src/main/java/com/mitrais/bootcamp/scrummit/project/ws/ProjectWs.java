package com.mitrais.bootcamp.scrummit.project.ws;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/project")
public class ProjectWs {

    private static final String template = "You get project:  %s!";

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public @ResponseBody String listAllProject() {
        return "You get all project";
    }

    @RequestMapping(path = "/name", method = RequestMethod.GET)
    public @ResponseBody String getProject(
            @RequestParam(value = "name", required = false,
            defaultValue = "Stranger") String name) {
        return String.format(template, name);
    }

}