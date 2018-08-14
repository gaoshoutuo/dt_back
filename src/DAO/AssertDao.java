package DAO;

import Entity.AssertEntity;
import Entity.LoginRecordEntity;

import java.util.List;

public interface AssertDao {

    void add(AssertEntity entity);
    void update(AssertEntity entity);
    void delete(AssertEntity entity);
    List<AssertEntity> query(); //hibernate api

}
