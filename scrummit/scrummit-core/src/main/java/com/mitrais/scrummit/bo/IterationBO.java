package com.mitrais.scrummit.bo;

import java.util.List;

import com.mitrais.scrummit.model.Iteration;
import com.mitrais.scrummit.model.Project;

public interface IterationBO {
	public Iteration findById(String id);
	
	public Iteration createIteration(Iteration iteration);
	
	public List<Iteration> findByProject(Project project);
	
	public Iteration findLastIterationByProject(Project project);
}
