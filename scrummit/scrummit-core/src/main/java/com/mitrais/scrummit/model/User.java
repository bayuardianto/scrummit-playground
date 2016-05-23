package com.mitrais.scrummit.model;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

@SuppressWarnings("serial")
public class User extends Common implements Serializable {

    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value = "userName")
    private String   userName;

    @Field(value = "password")
    private String   password;

    @Field(value = "firtsName")
    private String   firtsName;

    @Field(value = "lastName")
    private String   lastName;

    @Field(value = "email")
    private String   email;

    @Field(value = "userType")
    private String   userType;

    @JsonSerialize(using = ToStringSerializer.class)
    @DBRef(db = "smcentral")
    @Field(value = "assocOrgId")
    private Organization assocOrgId;


}
