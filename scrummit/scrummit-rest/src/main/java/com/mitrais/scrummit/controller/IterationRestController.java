package com.mitrais.scrummit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.BoardBO;
import com.mitrais.scrummit.bo.IterationBO;
import com.mitrais.scrummit.bo.ProjectBO;
import com.mitrais.scrummit.model.Board;
import com.mitrais.scrummit.model.Card;
import com.mitrais.scrummit.model.Iteration;
import com.mitrais.scrummit.model.Project;

@RestController
@RequestMapping("/rest/iteration")
public class IterationRestController {

	Logger logger = Logger.getLogger(IterationRestController.class);
	
	@Autowired
	IterationBO iteBO;
	
	@Autowired
	BoardBO boardBO;
	
	@Autowired
	ProjectBO projectBO;
	
	@RequestMapping(path = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Map<String, Object> create(@RequestBody Iteration iteration) {
		Map<String, Object> result = new HashMap();
		Project project = projectBO.getProject(iteration.getProject().getId().toString());
		Iteration existIt = iteBO.findByNameAndProject(iteration.getName(), project	);
		
		if (existIt != null){
			logger.info("Existing Iteration: " + existIt.getId());
			result.put("error", 1);
			result.put("message", "Iteration name is existed in this project");
		} else {
			result.put("error",  0);
			iteBO.createIteration(iteration);
		}
		return result;
    }
	
	@RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Iteration getIteration(@PathVariable("id") String id) {
        return iteBO.findById(id);
    }
	
	@RequestMapping(path = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Iteration> getIterations(@PathVariable("id") String id) {
        
		return iteBO.findByProject(projectBO.getProject(id));
    }
	
	@RequestMapping(path = "/last/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iteration getLastIteration(@PathVariable("name") String name) {
        
		return iteBO.findLastIterationByProject(projectBO.getProjectByProjectName(name));
    }
	
	@RequestMapping(path = "/board/{iteration}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Map<String, List<Card>> findIterationCards (@PathVariable("iteration") String iteration) {
		Iteration it = iteBO.findById(iteration);
		
		List<Card> todoList = new ArrayList<>();
		List<Card> inprogressList = new ArrayList<>();
		List<Card> completedList = new ArrayList<>();
		Board todoBoard = boardBO.findByIterationAndStatus(it, 0);
		if (todoBoard != null) todoList = todoBoard.getCards();
		
		Board inProgress = boardBO.findByIterationAndStatus(it, 1);
		if (inProgress != null) inprogressList = inProgress.getCards();
		
		Board completed = boardBO.findByIterationAndStatus(it, 2);
		if (completed != null) completedList = completed.getCards();
		
		Map<String, List<Card>> result = new HashMap<String, List<Card>>();
		
		result.put("todoList", todoList);
		result.put("inprogressList", inprogressList);
		result.put("completedList", completedList);
		
		return result;
		
	}
	
	@RequestMapping(path = "/board/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createBoard(@RequestBody Board board) {
		Board checkBoard = boardBO.findByIterationAndStatus(board.getIteration(), board.getStatus());
		if (checkBoard == null){
			boardBO.createBoard(board);
		} else {
			List<Card> cards = checkBoard.getCards();
			cards.addAll(board.getCards());
			checkBoard.setCards(cards);
			boardBO.saveBoard(checkBoard);
		}
	}
	
	@RequestMapping(path = "/board/{iteration}/{start}/{receive}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean saveBoard(@PathVariable("iteration") String iteration, @PathVariable("start") int start, @PathVariable("receive") int receive, @RequestBody Map<String, List<Card>> input) {
		Iteration it = iteBO.findById(iteration);
		Board board = boardBO.findByIterationAndStatus(it, start);
		
		List<Card> startCards = input.get("start");
		board.setCards(startCards);
		boardBO.saveBoard(board);
		
		if (receive>=0) {
			List<Card> receiveCards = input.get("receive");
			board = boardBO.findByIterationAndStatus(it, receive);
			if (board == null) {
				board = new Board();
				board.setStatus(receive);
				board.setIteration(it);
				board.setCards(receiveCards);
				boardBO.createBoard(board);
			} else {
				board.setCards(receiveCards);
				boardBO.saveBoard(board);
			}
			
		}
		
		return true;
	}
}
