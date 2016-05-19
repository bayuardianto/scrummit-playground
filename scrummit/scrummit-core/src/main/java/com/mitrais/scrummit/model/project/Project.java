package com.mitrais.scrummit.model.project;

import java.io.Serializable;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.mitrais.scrummit.model.common.Common;
@SuppressWarnings("serial")
@Document(collection = "project")
public class Project extends Common implements Serializable {
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId _id;

    @Field(value="name")
    private String name;

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

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId objectId) {
        this._id = objectId;
    }

    /*
     * @Override public String toString() {
     * 
     * return "\nProject{_id: '" + this._id + "' , name: '" + this.name +
     * "'}\n"; }
     */

}
