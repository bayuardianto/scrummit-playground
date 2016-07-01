package com.mitrais.scrummit.model;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

/**
 * Created by Fathoni on 16/05/30.
 */
@Document(collection = "cards")
public class Card extends Common implements Serializable {
	
    @Id
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId id;

    @Field(value = "title")
    private String title;

    @Field(value = "description")
    private String description;

    @Field(value = "points")
    private int points;

    @Field(value = "owner")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId owner;

    @Field(value = "epic_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId epicId;
    
    @Field(value = "task_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private ObjectId taskId;
    
    @DBRef
    @Field(value = "iteration")
    private Iteration iteration;

    @Field(value = "status")
    private int status;

    @Field(value = "estimate")
    private int estimate;

    public Card(){
        super();
    }

    public Card(String title){
        super();
        this.title = title;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ObjectId getOwner() {
        return owner;
    }

    public void setOwner(ObjectId owner) {
        this.owner = owner;
    }

    public ObjectId getEpicId() {
        return epicId;
    }

    public void setEpicId(ObjectId epicId) {
        this.epicId = epicId;
    }

    public ObjectId getTaskId() {
        return taskId;
    }

    public void setTaskId(ObjectId taskId) {
        this.taskId = taskId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

	public Iteration getIteration() {
		return iteration;
	}

	public void setIteration(Iteration iteration) {
		this.iteration = iteration;
	}
}

