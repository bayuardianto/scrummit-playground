package com.mitrais.scrummit.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Task;

@Repository
public interface TaskDAO extends CommonDAO<Task, String> {

    @Query(value = "{'owner.$id' : ?0}")
    public List<Task> findByCardId(ObjectId cardId);

    public List<Task> findByStatus(int status);
}
