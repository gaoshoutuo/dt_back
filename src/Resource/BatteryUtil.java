package Resource;

import DAO.BatteryDao;
import Entity.AssertEntity;
import Entity.BatteryNumberEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.util.List;

public class BatteryUtil implements BatteryDao {

    private static BatteryUtil bu;

    public static BatteryUtil getInstance(){
        if (bu==null)bu=new BatteryUtil();
        return bu;
    }
    @Override
    public void add(BatteryNumberEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(BatteryNumberEntity entity) {

    }

    @Override
    public void delete(BatteryNumberEntity entity) {

    }

    @Override
    public List<BatteryNumberEntity> query() {
        return null;
    }

    public int queryNumber(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String cusId=o.getString("cus_id");
        BatteryNumberEntity llll=null;
        Query query=session.createQuery("select stu from BatteryNumberEntity stu where cusId=:cusid ");
        query.setParameter("cusid",cusId);
        llll=(BatteryNumberEntity)query.uniqueResult();
        int number=llll.getBattNumber();

        return number;
    }
}
