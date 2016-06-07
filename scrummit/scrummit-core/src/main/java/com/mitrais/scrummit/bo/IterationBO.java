package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.Iteration;

public interface IterationBO {
	public Iteration findById(String id);
	
	public Iteration createIteration(Iteration iteration);
}
