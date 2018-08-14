package DAO;

import Entity.AgreeTableEntity;
import Entity.AssertEntity;

import java.util.List;

public interface AgreeDao {

    void add(AgreeTableEntity entity);
    void update(AgreeTableEntity entity);
    void delete(AgreeTableEntity entity);
    List<AgreeTableEntity> query(); //hibernate api


}
