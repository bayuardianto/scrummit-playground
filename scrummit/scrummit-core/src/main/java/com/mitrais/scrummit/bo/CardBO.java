package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.Card;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by Fathoni on 16/05/31.
 */
public interface CardBO {
    public List<Card> listAll();
    public Card getById(String id);
    public List<Card> getByIterationId(String iterationId);
    public List<Card> getByStatus(int status);
    public Card create(Card card);
    public Card update(Card card);
    public Card delete(String id);

}
