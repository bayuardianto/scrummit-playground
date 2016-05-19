package com.mitrais.scrummit.dao.project;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;

import com.mitrais.scrummit.dao.common.CommonDAOImpl;
import com.mitrais.scrummit.model.project.Project;

public class ProjectDAOCustomImpl extends CommonDAOImpl<Project, String> implements ProjectDAOCustom {

    public ProjectDAOCustomImpl(MongoEntityInformation<Project, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    public List<Project> findByName(String name) {
        List<Project> projects = getMongoOperations().find(query(where("name").is(name)), Project.class);
        return projects;
    }
}
