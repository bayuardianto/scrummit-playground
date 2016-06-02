package com.mitrais.scrummit.dao;

import com.mitrais.scrummit.model.Epic;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/**
 * Created by Fathoni on 16/06/01.
 */
public interface EpicDAO extends CommonDAO<Epic, String> {
    @Query("{ 'name' : ?0 }")
    public List<Epic> findByName(String name);
}
