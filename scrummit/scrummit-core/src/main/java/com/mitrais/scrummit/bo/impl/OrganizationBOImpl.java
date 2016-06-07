package com.mitrais.scrummit.bo.impl;

import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.OrganizationBO;
import com.mitrais.scrummit.dao.OrganizationDAO;
import com.mitrais.scrummit.model.Organization;

@Service
public class OrganizationBOImpl extends BaseBOImpl<Organization, OrganizationDAO> implements OrganizationBO {
    public OrganizationBOImpl() {
        super(true);
    }

}
