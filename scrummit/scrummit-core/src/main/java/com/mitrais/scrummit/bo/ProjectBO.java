package com.mitrais.scrummit.bo;

import java.util.List;

import com.mitrais.scrummit.model.Project;




public interface ProjectBO {
    public List<Project> listAllProject();

    public Project getProject(String id);

    public List<Project> getProjectByName(String projectName);

    Project getProjectByProjectName(String projectName);

    List<Project> getProjectByStatus(int status);

    public Project createProject(Project project);

    public Project updateProject(Project project);

    public Project deleteProject(String id);

    public List<Project> getProjectCreatedBy(String id);

    List<Project> getProjectByUser(String userId);
}
