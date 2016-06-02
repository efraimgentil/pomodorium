package me.efraimgentil.pomodorium.dao;

import java.util.List;

/**
 * Created by efraimgentil on 25/05/16.
 */
public interface IDatabase<T> {

    void insert(T t);
    void update(T t);
    void delete(T t);
    T find(int id);
    List<T> findAll();

}
