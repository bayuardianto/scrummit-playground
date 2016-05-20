package com.mitrais.scrummit.dao.project;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.dao.common.CommonDAO;
import com.mitrais.scrummit.model.project.Project;

@Repository
public interface ProjectDAO extends CommonDAO<Project, String> {
    @Query("{ 'name' : ?0 }")
    public List<Project> getName(String name);
}

