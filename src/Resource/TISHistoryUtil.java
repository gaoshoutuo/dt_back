package Resource;

import DAO.TISHistoryDAO;
import Entity.FixTestInsHistoryEntity;
import Entity.RegisterUserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class TISHistoryUtil implements TISHistoryDAO {
    public static TISHistoryUtil util;//单例的多种写法 枚举怎么写单例

    public static TISHistoryUtil getInstance(){
        if (util==null)
            util=new TISHistoryUtil();
        return util;
    }

    public TISHistoryUtil() {

    }
    @Override
    public void add(FixTestInsHistoryEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(FixTestInsHistoryEntity entity) {

    }

    @Override
    public void delete(FixTestInsHistoryEntity entity) {

    }

    @Override
    public List<FixTestInsHistoryEntity> query() {
        return null;
    }

    public JSONObject query(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String userStr=o.getString("user_str");

     /*   RegisterUserEntity llll = (RegisterUserEntity)session.createQuery("select userId,pwd from RegisterUserEntity stu where stu.userId like :userid and stu.pwd like :pwd")
                .setParameter("userid", userStr).setParameter("pwd", pwdStr)
                .uniqueResult();*/
        FixTestInsHistoryEntity llll=null;
        Query query=session.createQuery("select new FixTestInsHistoryEntity (dateHistory,textHistory,business,filepath,human) from FixTestInsHistoryEntity stu where userId=:userid");
        query.setParameter("userid",userStr);

        llll=(FixTestInsHistoryEntity)query.uniqueResult();
        //System.out.println(users.size()+"----------------------------------");
        hibUtil.commitClose();
        if (llll!=null){
            //System.out.println(llll.getCompany()+llll.getIdentity()+"--------------------------------------------");
            JSONObject loginReply=new JSONObject();
            loginReply.put("reply","tis_history");
            loginReply.put("date",llll.getDateHistory());
            loginReply.put("business",llll.getBusiness());
            loginReply.put("human",llll.getHuman());
            loginReply.put("text",llll.getTextHistory());
            loginReply.put("filepath",llll.getFilepath());
            // loginReply.put("pwd",llll.getPwd()); 不必要
            return loginReply;
        }else{
            System.out.println("--------------------------------------------");
            return null;
        }
    }

    public JSONObject queryCus(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String userStr=o.getString("cus_str");
        List<FixTestInsHistoryEntity> llll=null;    //userid 应该是
        //Query query=session.createQuery("select new FixTestInsHistoryEntity (dateHistory,textHistory,business,filepath,human) from FixTestInsHistoryEntity stu where userId=:userid");
        Query query=session.createQuery("select stu from FixTestInsHistoryEntity stu where userId=:userid");
        query.setParameter("userid",userStr);
        //llll=(FixTestInsHistoryEntity)query.uniqueResult();
        llll=(List<FixTestInsHistoryEntity>)query.getResultList();
        if (llll!=null){
            JSONObject cusHistory=new JSONObject();
            cusHistory.put("reply","tis_history_cus");
            JSONArray ja=new JSONArray();
            for(int i=0;i<llll.size();i++){
                FixTestInsHistoryEntity fhe=llll.get(i);
                JSONObject loginReply=new JSONObject();
                loginReply.put("date",fhe.getDateHistory());
                loginReply.put("business",fhe.getBusiness());
                loginReply.put("human",fhe.getHuman());
                loginReply.put("text",fhe.getTextHistory());
                loginReply.put("filepath",fhe.getFilepath());
                ja.put(loginReply);
            }
            cusHistory.put("array",ja);
            return cusHistory;
        }else{
            System.out.println("--------------------------------------------");
            return null;
        }
    }



    public JSONObject queryEng(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String engStr=o.getString("eng_str");
        List<FixTestInsHistoryEntity> llll=null;    //userid 应该是
        //Query query=session.createQuery("select new FixTestInsHistoryEntity (textHistory,business,filepath,human)  from FixTestInsHistoryEntity sp where engId=:engid"); 没有timestamp 就可以了 哦也
        Query query=session.createQuery("select sp  from FixTestInsHistoryEntity sp where engId=:engid");
        query.setParameter("engid",engStr);
        //llll=(FixTestInsHistoryEntity)query.uniqueResult();
        llll=(List<FixTestInsHistoryEntity>)query.getResultList();
        if (llll!=null){
            JSONObject engHistory=new JSONObject();
            engHistory.put("reply","tis_history_eng");
            JSONArray ja=new JSONArray();
            for(int i=0;i<llll.size();i++){
                FixTestInsHistoryEntity fhe=llll.get(i);
                JSONObject loginReply=new JSONObject();
                loginReply.put("date",fhe.getDateHistory());
                //loginReply.put("date",""+System.currentTimeMillis());
                loginReply.put("business",fhe.getBusiness());
                loginReply.put("human",fhe.getHuman());
                loginReply.put("text",fhe.getTextHistory());
                loginReply.put("filepath",fhe.getFilepath());
                ja.put(loginReply);
            }
            engHistory.put("array",ja);
            return engHistory;
        }else{
            System.out.println("--------------------------------------------");
            return null;
        }
    }
}
