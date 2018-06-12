package DAO;

import Entity.TesttableEntity;

import java.util.List;

public interface TestAI {
    void add(TesttableEntity entity);
    void update(TesttableEntity entity);
    void delete(TesttableEntity entity);
    List<TesttableEntity> query(); //hibernate api
}
