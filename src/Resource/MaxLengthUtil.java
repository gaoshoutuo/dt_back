package Resource;

import DAO.TestMaxLengthDao;
import Entity.MaxLengthTestEntity;
import org.hibernate.Session;

import java.util.List;

public class MaxLengthUtil implements TestMaxLengthDao {
    private static MaxLengthUtil tu;
    public static MaxLengthUtil getInstance(){
        if (tu==null){
            tu=new MaxLengthUtil();
        }
        return tu;
    }

    @Override
    public void add(MaxLengthTestEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(MaxLengthTestEntity entity) {

    }

    @Override
    public void delete(MaxLengthTestEntity entity) {

    }

    @Override
    public List<MaxLengthTestEntity> query() {
        return null;
    }
}
