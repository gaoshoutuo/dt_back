package Resource;

import DAO.FixMessageDao;
import Entity.FixMessageEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.util.List;

/**
 *  离线消息缓存表
 */

public class FixMessageUtil implements FixMessageDao {
    //blank1 工程师签名  blank2  客户签名  blank3   filepath


    private static FixMessageUtil tu;
    public static FixMessageUtil getInstance(){
        if (tu==null){
            tu=new FixMessageUtil();
        }
        return tu;
    }

    public FixMessageEntity dataMake(JSONObject obj){

        return null;
    }


    public FixMessageEntity makeFme(String arr[],int swch,String jsonArr[]){
        FixMessageEntity fme=new FixMessageEntity();
        fme.setTimeRecord(arr[0]);
        if (swch==1){//开关打开 加入
            fme.setJson1(jsonArr[0]);
            fme.setJson2(jsonArr[1]);
        }

        fme.setIdcId(arr[1]);
        fme.setIdcName(arr[2]);
        fme.setIdcLocation(arr[3]);
        fme.setIdcType(arr[4]);
        // 还是谨慎来使用arr 应为条件不明显
        fme.setUserId(arr[5]);
        fme.setEngId(arr[6]);
        fme.setBusinessType(arr[7]);
        fme.setIsWatch(arr[8]);
        fme.setEngName(arr[9]);

        fme.setBlank1(arr[10]);
        fme.setBlank2(arr[11]);
        fme.setBlank3(arr[12]);
        return fme;
    }

    /*public void makeFmeNoJson(String time,String ){  这就不必了
        FixMessageEntity fme=new FixMessageEntity();
        private String timeRecord;
        private String json1;
        private String json2;
        private String idcId;
        private String idcName;
        private String idcLocation;
        private String idcType;
        private String userId;
        private String engId;
        private String businessType;
        private String isWatch;
        private String engName;
        private String blank1;
        private String blank2;
        private String blank3;
    }*/

    @Override
    public void add(FixMessageEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(FixMessageEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update FixMessageEntity fme set fme.blank1 = :blank_1,fme.blank2=:blank_2 ,fme.blank3=:blank_3,fme.isWatch=:iswatch where fme.timeRecord= :timerecord";
        Query query = session.createQuery(hql);
        query.setParameter("blank_1",entity.getBlank1());
        query.setParameter("blank_2",entity.getBlank2());
        query.setParameter("blank_3",entity.getBlank3());
        query.setParameter("iswatch",entity.getIsWatch());
        query.setParameter("timerecord",entity.getTimeRecord());
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------更新FME成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void delete(FixMessageEntity entity) {

    }

    @Override
    public List<FixMessageEntity> query() {
        return null;
    }

    public FixMessageEntity query1(String timestamp) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select  fme from FixMessageEntity fme where fme.timeRecord=:timerecord";
        Query query=session.createQuery(hql);
        query.setParameter("timerecord",timestamp);
        FixMessageEntity fme=(FixMessageEntity)query.uniqueResult();
        System.out.println(fme.getIdcName()+"----------fme查询成功-------");
        hibUtil.commitClose();
        return fme;
    }

    public List<FixMessageEntity> query2(String userId,String type) {//登录的时候查的代办与历史  //type "0"待办  “2”历史
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select  fme from FixMessageEntity fme where fme.userId=:userid and fme.isWatch=:iswatch";
        Query query=session.createQuery(hql);
        query.setParameter("userid",userId);
        query.setParameter("iswatch",type);
        //FixMessageEntity fme=(FixMessageEntity)query.uniqueResult();
        List<FixMessageEntity> list=query.getResultList();
        System.out.println(list.size()+"----------fme查询成功-------");
        hibUtil.commitClose();
        return list;
    }

    public  List<FixMessageEntity> query3(String engid,String type){//工程师查
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select  fme from FixMessageEntity fme where fme.engId=:engid and fme.isWatch=:iswatch";
        Query query=session.createQuery(hql);
        query.setParameter("engid",engid);
        query.setParameter("iswatch",type);//“2”
        List<FixMessageEntity> list=query.getResultList();
        System.out.println(list.size()+"----------fme查询成功-------");
        hibUtil.commitClose();
        return list;
    }

}
