package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.Board;
import com.mitrais.scrummit.model.Iteration;

public interface BoardBO extends BaseBO<Board>{

	public Board findByIterationAndStatus(Iteration iteration, int status);
	
	public Board createBoard(Board board);
	
	public Board saveBoard(Board board);
	
	public Board findById(String id);
}
