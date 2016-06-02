package com.mitrais.scrummit.controller;

import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.model.OrganizationMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class OrganizationMemberController {
    @Autowired
    OrganizationMemberBO organizationMemberBO;

    @RequestMapping(path = "/orgmembers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List<OrganizationMember> getOrganizationMembers() {
        List<OrganizationMember> orgMembers = organizationMemberBO.getAll();
        return orgMembers;
    }
}
