package Resource;

import DAO.OutEightDao;
import Entity.OutLengthEightRowTableEntity;
import org.hibernate.Session;

import java.util.List;

public class OutEightUtil implements OutEightDao {
    private static OutEightUtil tu;
    public static OutEightUtil getInstance(){
        if (tu==null){
            tu=new OutEightUtil();
        }
        return tu;
    }
    @Override
    public void add(OutLengthEightRowTableEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(OutLengthEightRowTableEntity entity) {

    }

    @Override
    public void delete(OutLengthEightRowTableEntity entity) {

    }

    @Override
    public List<OutLengthEightRowTableEntity> query() {
        return null;
    }
}
