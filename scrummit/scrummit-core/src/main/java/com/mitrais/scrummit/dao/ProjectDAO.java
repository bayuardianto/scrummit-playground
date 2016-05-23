package com.mitrais.scrummit.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Project;

@Repository
public interface ProjectDAO extends CommonDAO<Project, String> {
    @Query("{ 'name' : ?0 }")
    public List<Project> getName(String name);
}

