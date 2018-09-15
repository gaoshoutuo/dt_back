package DAO;

import Entity.AgreeTableEntity;
import Entity.FixMessageEntity;

import java.util.List;

public interface FixMessageDao {
    void add(FixMessageEntity entity);
    void update(FixMessageEntity entity);
    void delete(FixMessageEntity entity);
    List<FixMessageEntity> query(); //hibernate api

}
