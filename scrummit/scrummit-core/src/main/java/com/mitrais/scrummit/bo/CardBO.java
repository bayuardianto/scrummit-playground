package com.mitrais.scrummit.bo;

import java.util.List;

import com.mitrais.scrummit.model.Card;
import com.mitrais.scrummit.model.Iteration;

/**
 * Created by Fathoni on 16/05/31.
 */
public interface CardBO extends BaseBO<Card> {
    public List<Card> listAll();
    public Card getById(String id);
    //public List<Card> getByIterationId(String iterationId);
    public List<Card> getByStatus(int status);
    public Card create(Card card);
    public Card update(Card card);
    public Card delete(String id);
    public List<Card> findByIteration(Iteration iteration);
}
