package com.mitrais.scrummit.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Project;

@Repository
public interface ProjectDAO extends CommonDAO<Project, String> {
    @Query("{ 'name' : ?0 }")
    public List<Project> getName(String name);

    Project findByName(String name);

    List<Project> findByStatus(int status);

    //@Query(value = "{ 'members.user_id' : ?0 }", fields = "{ 'questions.questionID' : 1 }")
    @Query("{ 'members.user_id' : ?0 }")
    List<Project> findByUserId(ObjectId userId);
}

