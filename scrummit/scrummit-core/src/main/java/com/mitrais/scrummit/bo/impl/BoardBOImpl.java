package com.mitrais.scrummit.bo.impl;

import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.BoardBO;
import com.mitrais.scrummit.dao.BoardDAO;
import com.mitrais.scrummit.model.Board;
import com.mitrais.scrummit.model.Iteration;

@Service
public class BoardBOImpl extends BaseBOImpl<Board, BoardDAO> implements BoardBO  {

	@Override
	public Board findByIterationAndStatus(Iteration iteration, int status) {
		// TODO Auto-generated method stub
		return currentDAO.findByIterationAndStatus(iteration, status);
	}

	@Override
	public Board createBoard(Board board) {
		return insert(board);
	}

	@Override
	public Board saveBoard(Board board) {
		// TODO Auto-generated method stub
		return save(board);
	}

	@Override
	public Board findById(String id) {
		// TODO Auto-generated method stub
		return currentDAO.findOne(id);
	}

}
