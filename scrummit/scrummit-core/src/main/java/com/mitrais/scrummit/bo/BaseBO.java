package com.mitrais.scrummit.bo;


public interface BaseBO<T> {
    public T save(T commonObject);

    public T insert(T commonObject);

    public T delete(T commonObject);

    public void setTenantName(String tenantName);
}
