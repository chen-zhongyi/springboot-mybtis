package com.chen.demo.services;

import com.chen.demo.models.BaseModel;

import java.util.List;

public interface BaseService<E extends BaseModel> {

    boolean add(E e) throws Exception;

    boolean update(E e) throws Exception;

    boolean delete(Long id);

    E findByID(Long id);

    List<E> fetchAll();
}
