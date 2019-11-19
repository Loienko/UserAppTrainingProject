package net.ukr.dreamsicle.dao;

import java.util.List;

public interface Dao<T> {

    T findById(Integer id);

    List<T> findAll();

    Integer create(T param);

    Integer delete(Integer id);

    Integer update(Integer id, T role);
}
