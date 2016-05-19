package com.mitrais.scrummit.dao.project;

import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.dao.common.CommonDAO;
import com.mitrais.scrummit.model.project.Project;

@Repository
public interface ProjectDAO extends ProjectDAOCustom, CommonDAO<Project, String> {

}

