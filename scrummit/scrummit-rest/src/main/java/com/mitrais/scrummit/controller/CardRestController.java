package com.mitrais.scrummit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.mitrais.scrummit.model.Card;
import com.mitrais.scrummit.model.Iteration;

@RestController
@RequestMapping("/rest/card/")
public class CardRestController {

	private static final Log log = LogFactory.getLog(CardRestController.class);
		
    @Autowired
    private CardBO cardBO;
    
    @Autowired
    private IterationBO iterationBO;

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
    
    @RequestMapping(path = "/{id}/status/{status}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public int updateStatus(@PathVariable("id") String id, @PathVariable("status") int status){
    	try {
    		Card card = cardBO.getById(id);
    		card.setStatus(status);
    		cardBO.update(card);
    	} catch (Exception e) {
    		return 0;
    	}
    	return 1;
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Card delete(@PathVariable("id") String id){
        return cardBO.delete(id);
    }

    @RequestMapping(path = "/bystatus/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Card> getCardByStatus(@PathVariable("status") int status){
        return cardBO.getByStatus(status);
    }
    
    @RequestMapping(path = "/byiteration/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> byIteration(@PathVariable("id") String id) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		Iteration it = iterationBO.findById(id);
		
		List<Card> cards = cardBO.findByIteration(it);
		
		response.put("cardList", cards);
		return response;
    }

}
