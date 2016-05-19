package com.mitrais.scrummit.model.common;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Field;

public class Common {
    @Field(value = "createdDate")
    private Date     createdDate;

    @Field(value = "modifiedDate")
    private Date     modifiedDate;

    @Field(value = "createdBy")
    private ObjectId createdBy;

    @Field(value = "modifiedBy")
    private ObjectId modifiedBy;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public ObjectId getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(ObjectId createdBy) {
        this.createdBy = createdBy;
    }

    public ObjectId getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(ObjectId modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
