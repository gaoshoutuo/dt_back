package Resource;

import DAO.BatteryDao;
import Entity.AssertEntity;
import Entity.BatteryNumberEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.util.List;

public class BatteryUtil implements BatteryDao {
    //不严谨好吧  多加一个idcid 字段

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
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update BatteryNumberEntity bne set bne.battNumber = :batteryNum where bne.idcId= :idcid";
        Query query = session.createQuery(hql);
        query.setParameter("batteryNum",entity.getBattNumber());
        query.setParameter("idcid",entity.getIdcId());
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(entity.getBattNumber()+"---------更新成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
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
        String idcid=o.getString("idc_id");
        BatteryNumberEntity llll=null;
        Query query=session.createQuery("select stu from BatteryNumberEntity stu where idcId=:idcid ");
        query.setParameter("idcid",idcid);
        llll=(BatteryNumberEntity)query.uniqueResult();
        if (llll==null){
            return 0;
        }
        int number=llll.getBattNumber();
        System.out.println(number);
        return number;
    }
}
