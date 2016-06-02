package com.mitrais.scrummit.controller;

import com.mitrais.scrummit.bo.CardBO;
import com.mitrais.scrummit.model.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/card/")
public class CardRestController {

    @Autowired
    private CardBO cardBO;

    @RequestMapping(path = "/cards", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Card> listAllCard() {
        return cardBO.listAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Card getCardById(@PathVariable("id") String id){
        return cardBO.getById(id);
    }

    @RequestMapping(path = "/create", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Card create(@RequestBody Card card){
        return cardBO.create(card);
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Card update(@RequestBody Card card){
        return cardBO.update(card);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Card delete(@PathVariable("id") String id){
        return cardBO.delete(id);
    }

    @RequestMapping(path = "/byiterationid/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCardByIterationId(@PathVariable("id") String id){
        return cardBO.getByIterationId(id);
    }

    @RequestMapping(path = "/bystatus/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCardByStatus(@PathVariable("status") int status){
        return cardBO.getByStatus(status);
    }

}
