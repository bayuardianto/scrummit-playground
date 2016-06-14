package com.mitrais.scrummit.bo.impl;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.TaskBO;
import com.mitrais.scrummit.dao.TaskDAO;
import com.mitrais.scrummit.model.Task;

@Service
public class TaskBOImpl extends BaseBOImpl<Task, TaskDAO> implements TaskBO {

    @Override
    public List<Task> listAll(ObjectId cardId) {
        return currentDAO.findByCardId(cardId);
    }

    @Override
    public Task getById(String id) {
        return currentDAO.findOne(id);
    }

    @Override
    public Task create(Task task) {
        return insert(task);
    }

    @Override
    public Task update(Task task) {
        return save(task);
    }

    @Override
    public Task delete(String id) {
        Task task = currentDAO.findOne(id);
        return task != null ? delete(task) : null;
    }

    @Override
    public List<Task> getByStatus(int status) {
        return currentDAO.findByStatus(status);
    }

}
