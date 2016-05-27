package com.mitrais.scrummit.model;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class Common {
    @CreatedDate
    @Field(value = "created_date")
    protected Date     createdDate;

    @LastModifiedDate
    @Field(value = "modified_date")
    protected Date     modifiedDate;

    @JsonSerialize(using = ToStringSerializer.class)
    @Field(value = "created_by")
    protected ObjectId createdBy;

    @JsonSerialize(using = ToStringSerializer.class)
    @Field(value = "modified_by")
    protected ObjectId modifiedBy;

    @Field(value = "is_deleted")
    protected Boolean  isDeleted;

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

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted != null ? isDeleted : false;
    }
}
