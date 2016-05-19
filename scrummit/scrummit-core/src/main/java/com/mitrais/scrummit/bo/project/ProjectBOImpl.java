package com.mitrais.scrummit.bo.project;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.dao.project.ProjectDAO;
import com.mitrais.scrummit.model.project.Project;

@Service
public class ProjectBOImpl implements ProjectBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);
    @Autowired
    ProjectDAO projectDAO;

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
    public List<Project> getProjectByName(String projectName) {
        log.info(String.format("find project with project name: %s", projectName));
        return projectDAO.findByName(projectName);
    }

    @Override
    public Project createProject(Project project) {
        log.info(String.format("save a project with project name: %s", project.getName()));
        return projectDAO.save(project);
    }

}
