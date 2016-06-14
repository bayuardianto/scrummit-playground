package com.mitrais.scrummit.bo;

import java.util.List;

import org.bson.types.ObjectId;

import com.mitrais.scrummit.model.Task;

public interface TaskBO extends BaseBO<Task> {

    List<Task> listAll(ObjectId cardId);

    Task getById(String id);

    Task create(Task task);

    Task update(Task task);

    Task delete(String id);

    List<Task> getByStatus(int status);

}
