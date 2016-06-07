package com.mitrais.scrummit.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Andreanus_P on 5/27/2016.
 */
public class ProjectView implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    private String name;

    private String description;

    private String createdByName;

    private String createdDate;

    private String status;

    private List<MemberView> members;

    public ProjectView() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public List<MemberView> getMembers() {
        return members;
    }

    public void setMembers(List<MemberView> members) {
        this.members = members;
    }
}

