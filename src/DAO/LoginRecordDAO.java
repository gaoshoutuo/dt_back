package DAO;

import Entity.LoginRecordEntity;

import java.util.List;

public interface LoginRecordDAO {

    void add(LoginRecordEntity entity);
    void update(LoginRecordEntity entity);
    void delete(LoginRecordEntity entity);
    List<LoginRecordEntity> query(); //hibernate api

}
