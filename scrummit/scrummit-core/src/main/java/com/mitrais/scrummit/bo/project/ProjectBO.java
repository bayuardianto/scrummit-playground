package com.mitrais.scrummit.bo.project;

import java.util.List;

import com.mitrais.scrummit.model.project.Project;


public interface ProjectBO {
    public List<Project> listAllProject();

    public Project getProject(String id);

    public List<Project> getProjectByName(String projectName);

    public Project createProject(Project project);
}
