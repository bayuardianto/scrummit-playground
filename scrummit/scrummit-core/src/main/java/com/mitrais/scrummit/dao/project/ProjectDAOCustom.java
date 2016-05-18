package com.mitrais.scrummit.dao.project;

import java.util.List;

import com.mitrais.scrummit.model.project.Project;

public interface ProjectDAOCustom {
    public List<Project> findByName(String name);
}
