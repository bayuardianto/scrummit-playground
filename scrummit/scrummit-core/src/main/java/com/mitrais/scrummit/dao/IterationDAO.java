package com.mitrais.scrummit.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Iteration;
import com.mitrais.scrummit.model.Project;

@Repository
public interface IterationDAO extends CommonDAO<Iteration, String> {

	public List<Iteration> findIterationByProject(Project project);
	
	public Iteration findByNameAndProject(String name, Project project);
}
