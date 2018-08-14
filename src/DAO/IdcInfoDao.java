package DAO;

import Entity.AssertEntity;
import Entity.IdcInfoEntity;

import java.util.List;

public interface IdcInfoDao {


    void add(IdcInfoEntity entity);
    void update(IdcInfoEntity entity);
    void delete(IdcInfoEntity entity);
    List<IdcInfoEntity> query(); //hibernate api

}
