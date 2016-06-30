package com.mitrais.scrummit.controller;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.mitrais.scrummit.bo.ActionLogBO;
import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.model.*;
import com.mitrais.scrummit.util.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.mitrais.scrummit.bo.ProjectBO;


@RestController
@RequestMapping("/rest")
public class ProjectRestController {
    @Autowired
    private ProjectBO           projectBO;
    
    @Autowired
    private OrganizationMemberBO organizationMemberBO;

    Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String template = "You get project:  %s!";

    @RequestMapping(path = "/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectView> getProject(@PathVariable("id") String id) throws GlobalException {

        if(!StringUtils.hasText(id))
            throw new GlobalException("Invalid Project Id request.");

        Project project = projectBO.getProject(id);

        if(project!=null)
            return new ResponseEntity<ProjectView>(toViewModel(project), HttpStatus.OK);
        else
            throw new GlobalException("Project data not found.");
    }

    @RequestMapping(path = "/project", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> listAllProject() {
        List<Project> projects = projectBO.listAllProject();
        return new ResponseEntity<>(toViewModel(projects), HttpStatus.OK);
    }

    @RequestMapping(path = "/project", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Project> create(@RequestBody Project project) throws GlobalException {
        if(project == null)
            throw new GlobalException("Invalid input parameters.");

        project = projectBO.createProject(project);
        	
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @RequestMapping(path = "/project/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Project> delete(@PathVariable("id") String id) throws GlobalException {
        if(!StringUtils.hasText(id))
            throw new GlobalException("Invalid Project Id request.");

        Project project = projectBO.deleteProject(id);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @RequestMapping(path = "/project/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<Project> update(@PathVariable("id") String id, @RequestBody ProjectView updatedProject)
    throws GlobalException {
        if(!StringUtils.hasText(id) || updatedProject == null)
            throw new GlobalException("Invalid update parameters.");

        Project currentProject = projectBO.getProject(id);
        Project project = projectBO.updateProject(toProjectModel(currentProject, updatedProject));
        return new ResponseEntity<Project>(project, HttpStatus.OK);
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


    @RequestMapping(path = "/createdby/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Project> getByProjectCreatedBy(@PathVariable("id") String id) {

        return projectBO.getProjectCreatedBy(id);

    }



    @ExceptionHandler(GlobalException.class)
    private ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
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

    private String getRoleName(int roleId) {
        if(roleId == CommonEnum.ProjectRole.PRODUCT_OWNER.ordinal()) {
            return CommonEnum.ProjectRole.PRODUCT_OWNER.toString().replace("_", " ");
        }
        else if(roleId == CommonEnum.ProjectRole.SCRUM_MASTER.ordinal()) {
            return CommonEnum.ProjectRole.SCRUM_MASTER.toString().replace("_", " ");
        }
        else if(roleId == CommonEnum.ProjectRole.MEMBER.ordinal()) {
            return CommonEnum.ProjectRole.MEMBER.toString().replace("_", " ");
        }
        else
            return "";
    }

    private List<MemberView> toListMemberView(List<Member> members) {
        List<MemberView> memberViews = new ArrayList<MemberView>();
        MemberView memberView = new MemberView();
        for(Member member : members)
        {
            memberView.setUserId(member.getUserId());
            OrganizationMember orgMember = organizationMemberBO.getByUserId(member.getUserId().toString());
            if(orgMember != null) {
                memberView.setUserName(orgMember.getFullName());
            }

            memberView.setRole(member.getRole());
            memberView.setRoleName(member.getRole() > -1 ? getRoleName(member.getRole()) : "");
        }
        memberViews.add(memberView);
        return memberViews;
    }

    private ProjectView toViewModel(Project project) {
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

        if(project.getMembers()!=null) {
            projectView.setMembers(toListMemberView(project.getMembers()));
        }

        return projectView;
    }

    private List<ProjectView> toViewModel(List<Project> projects) {
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

            if(project.getMembers()!=null)
            {
                projectView.setMembers(toListMemberView(project.getMembers()));

            }

            projectViews.add(projectView);
        }

        return projectViews;
    }

    private List<Member> toListMemberModel(List<MemberView> memberViews) {
        List<Member> members = new ArrayList<Member>();

        for(MemberView memberView : memberViews)
        {
            Member member = new Member();
            member.setUserId(memberView.getUserId());

            member.setRole(memberView.getRole());
            members.add(member);
        }

        return members;
    }

    private Project toProjectModel(Project project,ProjectView projectView) {
        //Project project = new Project();
        project.setName(projectView.getName());
        project.setDescription(projectView.getDescription());

        if(projectView.getMembers()!=null) {
            project.setMembers(toListMemberModel(projectView.getMembers()));
        }

        return project;
    }
}