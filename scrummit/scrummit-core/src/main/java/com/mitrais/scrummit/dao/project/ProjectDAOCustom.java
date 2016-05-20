package com.mitrais.scrummit.dao.project;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.dao.common.CommonDAOCustom;
import com.mitrais.scrummit.model.project.Project;

@Repository
public class ProjectDAOCustom extends CommonDAOCustom<Project, Serializable> {

    public List<Project> getName(String name) {
        System.out.println("\n\n Find dao \n\n");
        List<Project> projects = mongoTemplate.find(query(where("name").is(name)), Project.class);
        return projects;
    }
}
