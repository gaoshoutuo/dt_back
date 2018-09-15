package DAO;

import Entity.CountDownEntity;
import Entity.HotJsonEntity;

import java.util.List;

public interface HotJsonDao {

    void add(HotJsonEntity entity);
    void update(HotJsonEntity entity);
    void delete(HotJsonEntity entity);
    List<HotJsonEntity> query(); //hibernate api

}
