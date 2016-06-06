package com.mitrais.scrummit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.IterationBO;
import com.mitrais.scrummit.model.Iteration;

@RestController
@RequestMapping("/rest/iteration")
public class IterationRestController {

	@Autowired
	IterationBO iteBO;
	
	@RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iteration create(@RequestBody Iteration iteration) {
        return iteBO.createIteration(iteration);
    }
}
