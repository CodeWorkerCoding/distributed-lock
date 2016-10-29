package com.edu.nchu.distriuted.service;

/**
 * Created by Alen on 2016/10/30.
 */
public interface BaseService<T, ID> {

    public T create(T object);

    public T update(T object);

    public void delete(ID id);

    public T get(ID id);

}
