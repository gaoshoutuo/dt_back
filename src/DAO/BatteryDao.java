package DAO;

import Entity.AssertEntity;
import Entity.BatteryNumberEntity;

import java.util.List;

public interface BatteryDao {

    void add(BatteryNumberEntity entity);
    void update(BatteryNumberEntity entity);
    void delete(BatteryNumberEntity entity);
    List<BatteryNumberEntity> query(); //hibernate api


}
