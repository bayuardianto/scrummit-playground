package com.mitrais.scrummit.bo;

import java.util.List;

import com.mitrais.scrummit.model.Epic;

/**
 * Created by Fathoni on 16/06/01.
 */
public interface EpicBO extends BaseBO<Epic> {
    public List<Epic> listAll();
    public Epic getById(String id);
    public List<Epic> getByName(String name);
    public Epic create(Epic epic);
    public Epic update(Epic epic);
    public Epic delete(String id);
}
