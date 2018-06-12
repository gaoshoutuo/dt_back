package DAO;

import Entity.InfoEntity;

import java.util.List;

public interface TestInfo {
    void add(InfoEntity infoEntity);
    List<InfoEntity> query();
}
