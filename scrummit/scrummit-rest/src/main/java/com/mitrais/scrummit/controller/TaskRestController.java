package com.mitrais.scrummit.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.CardBO;
import com.mitrais.scrummit.bo.IterationBO;
import com.mitrais.scrummit.bo.TaskBO;
import com.mitrais.scrummit.model.Task;

@RestController
@RequestMapping("/rest/task/")
public class TaskRestController {
    private static final Log log = LogFactory.getLog(TaskRestController.class);

    @Autowired
    private TaskBO           taskBO;

    @Autowired
    private CardBO           cardBO;

    @Autowired
    private IterationBO      iterationBO;

    @RequestMapping(path = "/card/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Task> listAllCard(@PathVariable("id") String id) {
        return taskBO.listAll(new ObjectId(id));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Task getTaskById(@PathVariable("id") String id) {
        return taskBO.getById(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task create(@RequestBody Task task) {
        return taskBO.create(task);
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task update(@RequestBody Task task) {
        return taskBO.update(task);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Task delete(@PathVariable("id") String id) {
        return taskBO.delete(id);
    }

    @RequestMapping(path = "/bystatus/{status}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTaskByStatus(@PathVariable("status") int status) {
        return taskBO.getByStatus(status);
    }

    @RequestMapping(path = "/bycardid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> getTaskByCardId(@PathVariable("id") String id){return taskBO.listAll(new ObjectId(id));}
}
