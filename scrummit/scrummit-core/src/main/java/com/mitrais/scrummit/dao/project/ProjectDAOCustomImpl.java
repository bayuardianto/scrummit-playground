package com.mitrais.scrummit.dao.project;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

import com.mitrais.scrummit.model.project.Project;

public class ProjectDAOCustomImpl extends SimpleMongoRepository<Project, String> implements ProjectDAOCustom,
        MongoRepository<Project, String> {
    @Autowired
    MongoTemplate mongoTemplate;

    public ProjectDAOCustomImpl(MongoEntityInformation<Project, String> metadata, MongoOperations mongoOperations) {
        super(metadata, mongoOperations);
    }

    public List<Project> findByName(String name) {
        List<Project> projects = mongoTemplate.find(query(where("name").is(name)), Project.class);
        for (Iterator iterator = projects.iterator(); iterator.hasNext();) {
            Project project = (Project) iterator.next();
            System.out.println("Project: " + project.getName());

        }

        return projects;
    }
}
