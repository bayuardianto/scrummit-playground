package com.mitrais.scrummit.bo.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.ProjectBO;
import com.mitrais.scrummit.dao.ProjectCustomDAO;
import com.mitrais.scrummit.dao.ProjectDAO;
import com.mitrais.scrummit.model.Project;

@Service
public class ProjectBOImpl implements ProjectBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);
    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    ProjectCustomDAO         projectDAOCustom;

    @Override
    public List<Project> listAllProject() {
        log.info("find all project");
        return projectDAO.findAll();
    }

    @Override
    public Project getProject(String id) {
        log.info(String.format("find project with id: %s", id));
        return projectDAO.findOne(id);
    }

    @Override
    public List<Project> getProjectCreatedBy(String id) {
        log.info(String.format("find project createdBy id: %s", id));
        return projectDAO.findByCreatedBy(new ObjectId(id));
    }

    @Override
    public List<Project> getProjectByName(String projectName) {
        log.info(String.format("find project with project name: %s", projectName));
        return projectDAOCustom.getName(projectName);
    }

    @Override
    public Project createProject(Project project) {
        log.info(String.format("save a project with project name: %s", project.getName()));
        return projectDAO.save(project);
    }

    @Override
    public Project deleteProject(String id) {
        log.info(String.format("delete a project with project id: %s", id));
        Project project = projectDAO.findOne(id);
        project.setIsDeleted(true);
        return projectDAO.save(project);
    }

}
