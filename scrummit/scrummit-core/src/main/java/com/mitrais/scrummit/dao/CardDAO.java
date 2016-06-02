package com.mitrais.scrummit.dao;

import com.mitrais.scrummit.model.Card;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Fathoni on 16/05/31.
 */
@Repository
public interface CardDAO extends CommonDAO<Card, String> {
    @Query("{ 'status' : ?0 }")
    public List<Card> findByStatus(int status);

    @Query("{ 'iteration_id' : ?0 }")
    public List<Card> findByIterationId(ObjectId iterationId);

    public List<Card> findByTitle(String title);
}
