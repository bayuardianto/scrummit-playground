package com.mitrais.scrummit.dao;

import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Board;
import com.mitrais.scrummit.model.Iteration;

@Repository
public interface BoardDAO extends CommonDAO<Board, String>  {
	
	public Board findByIterationAndStatus(Iteration iteration, int status);
}
