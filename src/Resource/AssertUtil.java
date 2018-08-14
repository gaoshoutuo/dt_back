package Resource;

import DAO.AssertDao;
import Entity.AssertEntity;
import Entity.RegisterUserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.util.List;

public class AssertUtil implements AssertDao {
    private static AssertUtil sInstance;
    public static AssertUtil getInstance(){
        if (sInstance==null)sInstance=new AssertUtil();
        return sInstance;
    }

    @Override
    public void add(AssertEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(AssertEntity entity) {

    }

    @Override
    public void delete(AssertEntity entity) {

    }

    @Override
    public List<AssertEntity> query() {
        return null;
    }

    public String queryWithId(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String cusId=o.getString("cus_id");
        AssertEntity llll=null;
        Query query=session.createQuery("select stu from AssertEntity stu where cusId=:cusid ");
        query.setParameter("cusid",cusId);
        llll=(AssertEntity)query.uniqueResult();
        if (llll==null){
            JSONObject json2=new JSONObject();
            json2.put("reply","assert_reply");
            return json2.toString();
        }
        String json=llll.getJson1()+ llll.getJson2()+ llll.getJson3()+ llll.getJson4();
        System.out.println(json);
        JSONObject rejson=new JSONObject(json);
        rejson.put("reply","assert_reply");
        hibUtil.commitClose();
      return rejson.toString();
    }

    public String queryNumber(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String cusId=o.getString("cus_id");
        AssertEntity llll=null;
        Query query=session.createQuery("select stu from AssertEntity stu where cusId=:cusid ");
        query.setParameter("cusid",cusId);
        llll=(AssertEntity)query.uniqueResult();
        String json=llll.getJson1()+ llll.getJson2()+ llll.getJson3()+ llll.getJson4();
        System.out.println(json);
        JSONObject rejson=new JSONObject(json);
        rejson.put("reply","assert_reply");
        hibUtil.commitClose();
        return rejson.toString();
    }


}
