package com.epam.esm.repository.dao.impl;


import java.util.List;

public interface AbstractCRDRepository<T> {

    T create(T t);

    T read(int id);

    List<T> readAll();

    void delete(int id);

}
