package com.mitrais.scrummit.bo.impl;

import com.mitrais.scrummit.bo.CardBO;
import com.mitrais.scrummit.dao.CardDAO;
import com.mitrais.scrummit.model.Card;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fathoni on 16/05/31.
 */
@Service
public class CardBOImpl extends BaseBOImpl implements CardBO {
    private static final Log log = LogFactory.getLog(CardBOImpl.class);

    @Autowired
    CardDAO cardDAO;

    @Override
    public List<Card> listAll() {
        log.info("find all cards");
        resolveTenant();
        return cardDAO.findAll();
    }

    @Override
    public Card create(Card card) {
        log.info(String.format("save a card with card name: %s", card.getTitle()));
        resolveTenant();
        return cardDAO.save(card);
    }

    @Override
    public Card update(Card card) {
        log.info(String.format("update a card with card name: %s", card.getTitle()));
        resolveTenant();
        Card updateCard = cardDAO.findOne(String.valueOf(card.getId()));
        if (updateCard != null){
            updateCard.setTitle(card.getTitle());
            updateCard.setDescription(card.getDescription());
            updateCard.setPoints(card.getPoints());
            updateCard.setAssignees(card.getAssignees());
            updateCard.setEpicId(card.getEpicId());
            updateCard.setIterationId(card.getIterationId());
            updateCard.setTaskId(card.getTaskId());
            updateCard.setEstimate(card.getEstimate());
            updateCard.setCreatedBy(card.getCreatedBy());
            updateCard.setModifiedBy(card.getModifiedBy());
            updateCard.setModifiedDate(card.getModifiedDate());

        }
        return cardDAO.save(updateCard);
    }

    @Override
    public Card delete(String id) {
        log.info("delete a card");
        resolveTenant();
        Card deleteCard = cardDAO.findOne(id);
        if (deleteCard != null){
            deleteCard.setIsDeleted(true);
        }
        return cardDAO.save(deleteCard);
    }

    @Override
    public List<Card> getByIterationId(String iterationId) {
        log.info("getByIterationId");
        resolveTenant();
        return cardDAO.findByIterationId(new ObjectId(iterationId));
    }

    @Override
    public List<Card> getByStatus(int status) {
        resolveTenant();
        return cardDAO.findByStatus(status);
    }

    @Override
    public Card getById(String id) {
        log.info("getById");
        resolveTenant();
        return cardDAO.findOne(id);
    }


}

