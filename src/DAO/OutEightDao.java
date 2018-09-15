package DAO;

import Entity.FixMessageEntity;
import Entity.OutLengthEightRowTableEntity;

import java.util.List;

public interface OutEightDao {
    void add(OutLengthEightRowTableEntity entity);
    void update(OutLengthEightRowTableEntity entity);
    void delete(OutLengthEightRowTableEntity entity);
    List<OutLengthEightRowTableEntity> query(); //hibernate api
}
