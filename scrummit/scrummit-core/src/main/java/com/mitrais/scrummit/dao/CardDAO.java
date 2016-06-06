package com.mitrais.scrummit.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.mitrais.scrummit.model.Card;
import com.mitrais.scrummit.model.Iteration;


/**
 * Created by Fathoni on 16/05/31.
 */
@Repository
public interface CardDAO extends CommonDAO<Card, String> {
    @Query("{ 'status' : ?0 }")
    public List<Card> findByStatus(int status);

    public List<Card> findByTitle(String title);
    
    public List<Card> findByIteration(Iteration iteration);
}
