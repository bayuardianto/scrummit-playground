package com.mitrais.scrummit.bo;

import com.mitrais.scrummit.model.Epic;

import java.util.List;

/**
 * Created by Fathoni on 16/06/01.
 */
public interface EpicBO {
    public List<Epic> listAll();
    public Epic getById(String id);
    public List<Epic> getByName(String name);
    public Epic create(Epic epic);
    public Epic update(Epic epic);
    public Epic delete(String id);
}
