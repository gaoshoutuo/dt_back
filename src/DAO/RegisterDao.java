package DAO;

import Entity.RegisterUserEntity;

import java.util.List;

public interface RegisterDao {
    void add(RegisterUserEntity entity);
    void update(RegisterUserEntity entity);
    void delete(RegisterUserEntity entity);
    List<RegisterUserEntity> query(); //hibernate api
}
