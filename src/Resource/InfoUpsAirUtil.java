package Resource;

import DAO.InfoUpsAirDao;
import Entity.IdcInfoEntity;
import Entity.InfoUpsAirEntity;
import Entity.RegisterUserEntity;
import Entity.UpsAirAnyEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class InfoUpsAirUtil implements InfoUpsAirDao {

    private static InfoUpsAirUtil infoUpsAirUtil;
    public static InfoUpsAirUtil getInstance(){
        if (infoUpsAirUtil==null){
            infoUpsAirUtil=new InfoUpsAirUtil();
        }
        return infoUpsAirUtil;
    }
    @Override
    public void add(InfoUpsAirEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(InfoUpsAirEntity entity) {

    }

    @Override
    public void delete(InfoUpsAirEntity entity) {

    }

    @Override
    public List<InfoUpsAirEntity> query() {
        return null;
    }


    public InfoUpsAirEntity queryNumber(JSONObject jsonObject) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        Query query=session.createQuery("select iue from InfoUpsAirEntity iue where iue.idcId=:idcid");
        query.setParameter("idcid",jsonObject.getString("id"));
        InfoUpsAirEntity entity=(InfoUpsAirEntity)query.uniqueResult();
        hibUtil.commitClose();
        return entity;
    }
        //这两个错了
    public List<InfoUpsAirEntity> queryUserId(JSONObject jsonObject) {//根据userid 查tableReg的   机房 六项  本来要写到reg里的 这里改动太大 所以还是卸载这里吧
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        List<InfoUpsAirEntity> list=null;
        Query query=session.createQuery("select rue from InfoUpsAirEntity rue where rue.userId=:userid");
        query.setParameter("userid",jsonObject.getString("id"));
        //InfoUpsAirEntity entity=(InfoUpsAirEntity)query.uniqueResult();
        list= query.getResultList();
        if (list==null)return null;
        hibUtil.commitClose();
        return list;
    }
    //这两个错了
    public  List<InfoUpsAirEntity> queryUserName(JSONObject jsonObject) {//根据username 查机房 六项
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        List<InfoUpsAirEntity> list=null;
        Query query=session.createQuery("select iue from InfoUpsAirEntity iue where iue.userName=:username");
        query.setParameter("username",jsonObject.getString("name"));
        //InfoUpsAirEntity entity=(InfoUpsAirEntity)query.uniqueResult();
        list=query.getResultList();
        if (list==null)return null;
        hibUtil.commitClose();
        return list;
    }

    public void updateUps(JSONObject jsonObject) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update InfoUpsAirEntity iue set iue.upsNumber = iue.upsNumber+1,iue.blank1=:blank1 where iue.idcId= :idcid";
        Query query = session.createQuery(hql);
        query.setParameter("idcid",jsonObject.getString("id"));
        query.setParameter("blank1",jsonObject.getString("0"));
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------iue更新成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public void updateAir(JSONObject jsonObject) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update InfoUpsAirEntity iue set iue.airNumber = iue.airNumber+1 ,iue.blank1=:blank1 where iue.idcId= :idcid";
        Query query = session.createQuery(hql);
        query.setParameter("idcid",jsonObject.getString("id"));
        query.setParameter("blank1",jsonObject.getString("0"));
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------iue更新成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public void updateJsonData(JSONObject jsonObject) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update InfoUpsAirEntity iue set iue.jsonData = :jsondata,iue.blank1=:blank1 where iue.userId= :userid";
        Query query = session.createQuery(hql);
        query.setParameter("userid",jsonObject.getString("id"));
        query.setParameter("blank1",jsonObject.getString("1"));
        query.setParameter("jsondata",jsonObject.getString("json"));
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------iue更新成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }
    //闲置
    public InfoUpsAirEntity queryAll(JSONObject jsonObject) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        Query query=session.createQuery("select iue from InfoUpsAirEntity iue where iue.idcId=:idcid");
        query.setParameter("idcid",jsonObject.getString("id"));
        InfoUpsAirEntity entity=(InfoUpsAirEntity)query.uniqueResult();
        hibUtil.commitClose();
        return entity;
    }

    public JSONObject infoQuery(JSONObject jsonObject, Socket socket){
        List<InfoUpsAirEntity> list=null;
        if(jsonObject.getString("name").equals("007")){//按id 查找  id 直接找  no  算了 直接拿idc id 去查userid 与 username吧  这个不方便
            list=queryUserId(jsonObject);
            //UpsAirUtil.getInstance().queryType(null);
           /* jsonObject.put("user_id",jsonObject.getString("id"));
            IdcInfoEntity ife=IdcInfoUtil.getInstance().queryIdc2(jsonObject);*/
        }else if(jsonObject.getString("id").equals("007")){//按姓名 查找
            list=queryUserName(jsonObject);
            IdcInfoUtil.getInstance().query3(jsonObject);
        }
        JSONObject obj=new JSONObject();
        obj.put("reply","info_reply");
        if (list.size()==0){
            return null;
        }
        //obj.put("user_id","");
        //obj.put("user_name","");
        JSONArray ja=new JSONArray();
        for(int i=0;i<list.size();i++){
            InfoUpsAirEntity entity=list.get(i);
            JSONObject iuaeJson=new JSONObject();
            iuaeJson.put("idc_id",entity.getIdcId());
            iuaeJson.put("idc_name",entity.getIdcName());
            iuaeJson.put("idc_location",entity.getIdcLocationSimple());
            iuaeJson.put("idc_type",entity.getIdcType());
            iuaeJson.put("ups_number",entity.getUpsNumber());
            iuaeJson.put("air_number",entity.getAirNumber());
            ja.put(iuaeJson);
        }
        obj.put("array",ja);
        try {
            socket.getOutputStream().write(obj.toString().getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //
       /* JSONObject hotJson=new JSONObject();  要开新表格
        hotJson.put("id",)
        updateJsonData(null);*/

        // 似乎就是这样 这个json 可以存起来这是第一点  第二点就是放到内存里面做热数据缓存也更好一点  所以速度快  有的json长度都是几十k级别的  好了 做一次
        //思路  在此处  当发生修改的时候  blank1 置0  当json_data置1时可以直接去拿  当blank2置0的时候开始一次整体的工作  并且


        return obj;
    }

    //查blank1

}
