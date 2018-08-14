package DAO;

import Entity.AssertEntity;
import Entity.IdcAssetEntity;

import java.util.List;

public interface IdcAssetDao {

    void add(IdcAssetEntity entity);
    void update(IdcAssetEntity entity);
    void delete(IdcAssetEntity entity);
    List<IdcAssetEntity> query(); //hibernate api

}
