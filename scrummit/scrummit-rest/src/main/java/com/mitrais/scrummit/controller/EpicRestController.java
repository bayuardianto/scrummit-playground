package com.mitrais.scrummit.controller;

import com.mitrais.scrummit.bo.EpicBO;
import com.mitrais.scrummit.model.Epic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Fathoni on 16/06/01.
 */
@RestController
@RequestMapping(path = "/rest/epic/")
public class EpicRestController {

    @Autowired
    EpicBO epicBO;

    @RequestMapping(path = "/epics", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Epic> listAllEpics(){
        return epicBO.listAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Epic getEpicById(@PathVariable("id") String id){
        return epicBO.getById(id);
    }

    @RequestMapping(path = "/byname/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Epic> getEpicByName(@PathVariable("name") String name){
        return epicBO.getByName(name);
    }

    @RequestMapping(path = "/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Epic create(@RequestBody Epic epic){
        return epicBO.create(epic);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Epic update(@RequestBody Epic epic){
        return epicBO.update(epic);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Epic delete(@PathVariable("id") String id){
        return epicBO.delete(id);
    }
}
