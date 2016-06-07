package com.mitrais.scrummit.model;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
@Document(collection = "projects")
public class Project extends Common implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value="name")
    private String name;

    @Field(value="description")
    private String description;

    @Field(value="status")
    private int status;

    //@Field(value = "details")
    //private Detail       details;

    @Field(value = "members")
    private List<Member> members;

    public Project(String name) {
        super();
        this.name = name;
    }

    public Project() {
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId objectId) {
        this.id = objectId;
    }
    /*
    public Detail getDetails() {
        return details;
    }

    public void setDetails(Detail details) {
        this.details = details;
    }
    */
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

}

/*class Member {
    @JsonSerialize(using = ToStringSerializer.class)
    @Field(value = "user_id")
    private ObjectId userId;

    @Field(value = "role")
    private int   role;

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}*/

/*
class Detail {

    @Field(value = "name")
    private String name;

    @Field(value = "description")
    private String description;

    @Field(value = "status")
    private String status;

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

}
*/
