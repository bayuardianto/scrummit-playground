package com.mitrais.scrummit.bo.impl;

import java.util.List;

import com.mitrais.scrummit.bo.OrganizationMemberBO;
import com.mitrais.scrummit.dao.OrganizationMemberDAO;
import com.mitrais.scrummit.model.OrganizationMember;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrganizationMemberBOImpl implements OrganizationMemberBO {
    private static final Log log = LogFactory.getLog(ProjectBOImpl.class);
    @Autowired
    OrganizationMemberDAO organizationMemberDAO;

    @Override
    public OrganizationMember getByUserId(String userId) {
        return organizationMemberDAO.findByUserId(new ObjectId(userId));
    }

    @Override
    public String testString() {
        return "testetas";
    }
}