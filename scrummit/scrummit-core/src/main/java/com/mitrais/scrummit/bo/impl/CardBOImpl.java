package com.mitrais.scrummit.bo.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.mitrais.scrummit.bo.CardBO;
import com.mitrais.scrummit.dao.CardDAO;
import com.mitrais.scrummit.model.Card;
import com.mitrais.scrummit.model.Iteration;

/**
 * Created by Fathoni on 16/05/31.
 */
@Service
public class CardBOImpl extends BaseBOImpl<Card, CardDAO> implements CardBO {
    private static final Log log = LogFactory.getLog(CardBOImpl.class);

    @Override
    public List<Card> listAll() {
        log.info("find all cards");
        return currentDAO.findAll();
    }

    @Override
    public Card create(Card card) {
        log.info(String.format("save a card with card name: %s", card.getTitle()));
        return insert(card);
    }

    @Override
    public Card update(Card card) {
        log.info(String.format("update a card with card name: %s", card.getTitle()));

        Card updateCard = currentDAO.findOne(String.valueOf(card.getId()));
        if (updateCard != null){
            updateCard.setTitle(card.getTitle());
            updateCard.setDescription(card.getDescription());
            updateCard.setPoints(card.getPoints());
            updateCard.setOwner(card.getOwner());
            updateCard.setEpicId(card.getEpicId());
            updateCard.setIteration(card.getIteration());
            updateCard.setTaskId(card.getTaskId());
            updateCard.setEstimate(card.getEstimate());
            updateCard.setCreatedBy(card.getCreatedBy());
            updateCard.setModifiedBy(card.getModifiedBy());
            updateCard.setModifiedDate(card.getModifiedDate());
            updateCard.setStatus(card.getStatus());
        }
        return save(updateCard);
    }

    @Override
    public Card delete(String id) {
        log.info("delete a card");
        Card deleteCard = currentDAO.findOne(id);
        return deleteCard != null ? delete(deleteCard) : null;
    }

    @Override
    public List<Card> getByStatus(int status) {
        return currentDAO.findByStatus(status);
    }

    @Override
    public Card getById(String id) {
        log.info("getById");
        return currentDAO.findOne(id);
    }

	@Override
	public List<Card> findByIteration(Iteration iteration) {
		log.info("findByIteration");
		return currentDAO.findByIteration(iteration);
	}


}

