package com.mitrais.scrummit.bo.impl;

import java.util.Iterator;
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
public class ProjectBOImpl extends BaseBOImpl<Project, ProjectDAO> implements ProjectBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);



    @Autowired
    ProjectCustomDAO         projectDAOCustom;

    @Override
    public List<Project> listAllProject() {
        log.info("find all project");
        List<Project> projects = currentDAO.findAll();

        Iterator<Project> projectIterator = projects.iterator();
        while (projectIterator.hasNext()) {
            Project project = projectIterator.next(); // must be called before you can call i.remove()
            if(project.getIsDeleted()) {
                projectIterator.remove();
            }
        }

        return projects;
    }

    @Override
    public Project getProject(String id) {
        log.info(String.format("find project with id: %s", id));
        return currentDAO.findOne(id);
    }

    @Override
    public List<Project> getProjectCreatedBy(String id) {
        log.info(String.format("find project createdBy id: %s", id));
        return currentDAO.findByCreatedBy(new ObjectId(id));
    }

    @Override
    public List<Project> getProjectByName(String projectName) {
        log.info(String.format("find project with project name: %s", projectName));
        return projectDAOCustom.getName(projectName);
    }

    @Override
    public Project createProject(Project project) {
        log.info(String.format("save a project with project name: %s", project.getName()));
        /*todo*/
        //set createdBy to the current user
        project.setStatus(CommonEnum.ProjectStatus.IN_PROGRESS.ordinal());
        return insert(project);
    }

    @Override
    public Project updateProject(Project project) {
        log.info(String.format("update a project with project id: %s", project.getId().toString()));
        return save(project);
    }

    @Override
    public Project deleteProject(String id) {
        log.info(String.format("delete a project with project id: %s", id));
        Project project = currentDAO.findOne(id);
        project.setIsDeleted(true);
        return delete(project);
    }

    @Override
    public List<Project> getProjectByStatus(int status) {
        return currentDAO.findByStatus(status);
    }

    @Override
    public Project getProjectByProjectName(String name) {
        return currentDAO.findByName(name);
    }

    @Override
    public List<Project> getProjectByUser(String userId) {
        return currentDAO.findByUserId(new ObjectId(userId));
    }
}
