package com.mitrais.scrummit.bo.impl;

import java.util.List;

import com.mitrais.scrummit.model.CommonEnum;
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
public class ProjectBOImpl extends BaseBOImpl implements ProjectBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);
    @Autowired
    ProjectDAO projectDAO;

    @Autowired
    ProjectCustomDAO         projectDAOCustom;

    @Override
    public List<Project> listAllProject() {
        log.info("find all project");
        resolveTenant();
        return projectDAO.findAll();
    }

    @Override
    public Project getProject(String id) {
        log.info(String.format("find project with id: %s", id));
        resolveTenant();
        return projectDAO.findOne(id);
    }

    @Override
    public List<Project> getProjectCreatedBy(String id) {
        log.info(String.format("find project createdBy id: %s", id));
        resolveTenant();
        return projectDAO.findByCreatedBy(new ObjectId(id));
    }

    @Override
    public List<Project> getProjectByName(String projectName) {
        log.info(String.format("find project with project name: %s", projectName));
        resolveTenant();
        return projectDAOCustom.getName(projectName);
    }

    @Override
    public Project createProject(Project project) {
        log.info(String.format("save a project with project name: %s", project.getName()));
        resolveTenant();
        /*todo*/
        //set createdBy to the current user
        project.setStatus(CommonEnum.ProjectStatus.IN_PROGRESS.ordinal());
        project.setModifiedBy(null);
        project.setModifiedBy(null);
        return projectDAO.save(project);
    }

    @Override
    public Project deleteProject(String id) {
        log.info(String.format("delete a project with project id: %s", id));
        resolveTenant();
        Project project = projectDAO.findOne(id);
        project.setIsDeleted(true);
        return projectDAO.save(project);
    }

    @Override
    public List<Project> getProjectByStatus(int status) {
        resolveTenant();
        return projectDAO.findByStatus(status);
    }

    @Override
    public Project getProjectByProjectName(String name) {
        resolveTenant();
        return projectDAO.findByName(name);
    }

    @Override
    public List<Project> getProjectByUser(String userId) {
        resolveTenant();
        return projectDAO.findByUserId(new ObjectId(userId));
    }
}
