package com.mitrais.scrummit.dao.project;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.project.Project;

@Repository
public interface ProjectDAO extends ProjectDAOCustom, MongoRepository<Project, String> {

}

