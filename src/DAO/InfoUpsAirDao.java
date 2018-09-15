package DAO;

import Entity.AgreeTableEntity;
import Entity.InfoUpsAirEntity;

import java.util.List;

public interface InfoUpsAirDao {

    void add(InfoUpsAirEntity entity);
    void update(InfoUpsAirEntity entity);
    void delete(InfoUpsAirEntity entity);
    List<InfoUpsAirEntity> query(); //hibernate api

}
