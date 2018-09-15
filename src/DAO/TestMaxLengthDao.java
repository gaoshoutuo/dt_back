package DAO;

import Entity.FixMessageEntity;
import Entity.MaxLengthTestEntity;
import Entity.OutLengthEightRowTableEntity;

import java.util.List;

public interface TestMaxLengthDao {
    void add(MaxLengthTestEntity entity);
    void update(MaxLengthTestEntity entity);
    void delete(MaxLengthTestEntity entity);
    List<MaxLengthTestEntity> query(); //hibernate api
}
