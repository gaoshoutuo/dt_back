package DAO;

import Entity.FixTestInsHistoryEntity;
import Entity.LoginRecordEntity;

import java.util.List;

public interface TISHistoryDAO {


    void add(FixTestInsHistoryEntity entity);
    void update(FixTestInsHistoryEntity entity);
    void delete(FixTestInsHistoryEntity entity);
    List<FixTestInsHistoryEntity> query(); //hibernate api

}
