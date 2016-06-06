package com.mitrais.scrummit.bo.impl;

import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.IterationBO;
import com.mitrais.scrummit.dao.IterationDAO;
import com.mitrais.scrummit.model.Iteration;

@Service
public class IterationBOImpl extends BaseBOImpl<Iteration, IterationDAO> implements IterationBO {

	@Override
	public Iteration findById(String id) {
		return currentDAO.findOne(id);
	}

	@Override
	public Iteration createIteration(Iteration iteration) {
		return insert(iteration);
	}

}
