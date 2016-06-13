package com.mitrais.scrummit.bo.impl;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.IterationBO;
import com.mitrais.scrummit.dao.IterationDAO;
import com.mitrais.scrummit.model.Iteration;
import com.mitrais.scrummit.model.Project;

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

	@Override
	public List<Iteration> findByProject(Project project) {
		
		return currentDAO.findIterationByProject(project);
	}

	@Override
	public Iteration findLastIterationByProject(Project project) {
		List<Iteration> iterations = currentDAO.findIterationByProject(project);
		if (iterations.size()>0)
			return iterations.get(iterations.size()-1);
		else
			return new Iteration();
	}

}
