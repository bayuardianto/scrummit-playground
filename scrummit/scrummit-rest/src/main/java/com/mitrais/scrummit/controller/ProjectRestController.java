package com.mitrais.scrummit.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.model.CommonEnum;
import com.mitrais.scrummit.model.OrganizationMember;
import com.mitrais.scrummit.model.ProjectView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mitrais.scrummit.bo.ProjectBO;
import com.mitrais.scrummit.model.Project;


@RestController
@RequestMapping("/rest")
public class ProjectRestController {
    @Autowired
    private ProjectBO           projectBO;

    @Autowired
    private OrganizationMemberBO organizationMemberBO;

//   private ProjectMapper projectMapper = new ProjectMapper();

    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String template = "You get project:  %s!";

    @RequestMapping(path = "/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ProjectView> listAllProject() {
        List<Project> projects = projectBO.listAllProject();
        if(projects != null)
            return ToViewModel(projects);
            //return projects;
        else
            return null;
    }

    @RequestMapping(path = "/project", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project create(@RequestBody Project project) {
        return projectBO.createProject(project);
    }

    @RequestMapping(path = "/project/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project delete(@PathVariable("id") String id) {
        return projectBO.deleteProject(id);
    }

    @RequestMapping(path = "/project/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public Project update(@PathVariable("id") String id) {
        return projectBO.deleteProject(id);
    }

    @RequestMapping(path = "/projectdetail/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> listProjectByUser(@PathVariable("id") String id) {
        List<Project> projects = projectBO.getProjectByUser(id);
        return projects;
    }

    @RequestMapping(path = "/proj/{param}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Project getProjectByName(@PathVariable("param") String name) {
        Project projects = projectBO.getProjectByProjectName(name);
        return projects;
    }

    @RequestMapping(path = "/project/{status}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> listProjectByUser(@PathVariable("status") int status) {
        List<Project> projects = projectBO.getProjectByStatus(status);
        return projects;
    }

    @RequestMapping(path="/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getTest() {return "test";}


    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Project getProject(@PathVariable("id") String id) {
        return projectBO.getProject(id);
    }


    @RequestMapping(path = "/createdby/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> getByProjectCreatedBy(@PathVariable("id") String id) {

        return projectBO.getProjectCreatedBy(id);

    }




    private String GetProjectStatus(int status) {
        if(status == CommonEnum.ProjectStatus.CREATED.ordinal()) {
            return CommonEnum.ProjectStatus.CREATED.toString();
        }
        else if(status == CommonEnum.ProjectStatus.FINISHED.ordinal()) {
            return CommonEnum.ProjectStatus.FINISHED.toString();
        }
        else if(status == CommonEnum.ProjectStatus.IN_PROGRESS.ordinal()) {
            return CommonEnum.ProjectStatus.IN_PROGRESS.toString().replace("_", " ");
        }
        else
            return "";
    }

    public List<ProjectView> ToViewModel(List<Project> projects) {
        if(projects.isEmpty()) {
            return null;
        }

        List<ProjectView> projectViews = new ArrayList<ProjectView>();
        for(Project project : projects) {
            ProjectView projectView = new ProjectView();
            projectView.setId(project.getId());
            projectView.setName(project.getName());
            projectView.setDescription(project.getDescription());
            projectView.setCreatedDate(project.getCreatedDate() != null ? formatter.format(project.getCreatedDate()) : "");
            if(project.getCreatedBy()!=null)
            {

                OrganizationMember orgMember = organizationMemberBO.getByUserId(project.getCreatedBy().toString());
                if(orgMember != null)
                projectView.setCreatedByName(orgMember.getFullName());
            }
            projectView.setStatus(project.getStatus() > -1 ? GetProjectStatus(project.getStatus()) : "");
            projectViews.add(projectView);
        }

        return projectViews;
    }
}