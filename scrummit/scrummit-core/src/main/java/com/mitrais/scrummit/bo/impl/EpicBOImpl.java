package com.mitrais.scrummit.bo.impl;

import com.mitrais.scrummit.bo.EpicBO;
import com.mitrais.scrummit.dao.EpicDAO;
import com.mitrais.scrummit.model.Epic;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Fathoni on 16/06/01.
 */
@Service
public class EpicBOImpl implements EpicBO {
    private static final Log log = LogFactory.getLog(CardBOImpl.class);

    @Autowired
    EpicDAO epicDAO;

    @Override
    public List<Epic> listAll() {
        log.info("find all epics");
        return epicDAO.findAll();
    }

    @Override
    public Epic getById(String id) {
        log.info("find epic by id");
        return epicDAO.findOne(id);
    }

    @Override
    public List<Epic> getByName(String name) {
        log.info("find epics by name");
        return epicDAO.findByName(name);
    }

    @Override
    public Epic create(Epic epic) {
        log.info("save epic");
        return epicDAO.save(epic);
    }

    @Override
    public Epic update(Epic epic) {
        log.info("update epic");
        Epic updateEpic = epicDAO.findOne(String.valueOf(epic.getId()));
        if (updateEpic != null){
            updateEpic.setName(epic.getName());
            updateEpic.setDescription(epic.getDescription());
        }
        return epicDAO.save(updateEpic);
    }

    @Override
    public Epic delete(String id) {
        log.info("delete epic");
        Epic epic = epicDAO.findOne(id);
        if (epic != null){
            epic.setIsDeleted(true);
        }
        return epicDAO.save(epic);
    }
}
