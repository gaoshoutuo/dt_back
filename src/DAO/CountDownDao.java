package DAO;

import Entity.AssertEntity;
import Entity.CountDownEntity;

import java.util.List;

public interface CountDownDao {

    void add(CountDownEntity entity);
    void update(CountDownEntity entity);
    void delete(CountDownEntity entity);
    List<CountDownEntity> query(); //hibernate api

}
