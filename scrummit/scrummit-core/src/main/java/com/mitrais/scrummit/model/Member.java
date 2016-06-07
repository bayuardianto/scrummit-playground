package com.mitrais.scrummit.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by Andreanus_P on 6/6/2016.
 */
public class Member {
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
}
