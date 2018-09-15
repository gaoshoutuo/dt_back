package DAO;

import Entity.AgreeTableEntity;
import Entity.UpsAirAnyEntity;
import net.sf.json.JSONObject;

import java.util.List;

public interface UpsAirDao {
    void add(UpsAirAnyEntity entity);
    void update(UpsAirAnyEntity entity);
    void delete(UpsAirAnyEntity entity);
    List<UpsAirAnyEntity> query(UpsAirAnyEntity entity); //hibernate api


}
