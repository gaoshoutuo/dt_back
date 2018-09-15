package Resource;

import DAO.IdcInfoDao;
import Engineering.JsonUtil;
import Entity.AgreeTableEntity;
import Entity.IdcAssetEntity;
import Entity.IdcInfoEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.List;

public class IdcInfoUtil implements IdcInfoDao {
    private static IdcInfoUtil idcInfoUtil;
    public static IdcInfoUtil getInstance(){
        if (idcInfoUtil==null)idcInfoUtil=new IdcInfoUtil();
        return idcInfoUtil;
    }



    public IdcInfoEntity reverse(JSONObject jsonObject){
        IdcInfoEntity iie=new IdcInfoEntity();
        iie.setIdcId(jsonObject.getString("idc_id"));
        iie.setIdcName(jsonObject.getString("idc_name"));
        iie.setIdcType(jsonObject.getString("idc_type"));
        iie.setUserId(jsonObject.getString("user_id"));
        iie.setUserName(jsonObject.getString("user_name"));
        iie.setIdcLocationDeep(jsonObject.getString("idc_location_deep"));
        iie.setIdcLocationSimple(jsonObject.getString("idc_location_simple"));
        return iie;
    }

    @Override
    public void add(IdcInfoEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(IdcInfoEntity entity) {

    }

    @Override
    public void delete(IdcInfoEntity entity) {

    }

    @Override
    public List<IdcInfoEntity> query() {
        return null;
    }

    public JSONObject queryIdc(JSONObject o) {//后期拉报表用
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String userId=o.getString("user_id");
        List<IdcInfoEntity> llll=null;
        Query query=session.createQuery("select stu from IdcInfoEntity stu where userId=:userid");
        query.setParameter("userid",userId);
        llll=query.getResultList();
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("reply","idc_info_reply");
        JSONArray ja=new JSONArray();
      for (int i=0;i<llll.size();i++){
          IdcInfoEntity iie=llll.get(i);
          JSONObject item=new JSONObject();
          item.put("idc_id",iie.getIdcId());
          item.put("idc_s_location",iie.getIdcLocationSimple());
          item.put("idc_name",iie.getIdcName());
          item.put("idc_type",iie.getIdcType());
          ja.put(item);
      }
      jsonObj.put("array",ja);
        return jsonObj;

    }

    public JSONObject query3(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String userId=o.getString("name");
        List<IdcInfoEntity> llll=null;
        Query query=session.createQuery("select stu from IdcInfoEntity stu where userName=:username");
        query.setParameter("username",userId);
        llll=query.getResultList();
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("reply","idc_info_reply");
        JSONArray ja=new JSONArray();
        for (int i=0;i<llll.size();i++){
            IdcInfoEntity iie=llll.get(i);
            JSONObject item=new JSONObject();
            item.put("idc_id",iie.getIdcId());
            item.put("idc_s_location",iie.getIdcLocationSimple());
            item.put("idc_name",iie.getIdcName());
            item.put("idc_type",iie.getIdcType());
            ja.put(item);
        }
        jsonObj.put("array",ja);
        return jsonObj;

    }

    //闲置的方法
    public void simpleMe(JSONObject jsonObject, Socket socket){
        try {
        if(jsonObject.getString("ide").equals("维保人员")){
            JSONObject idcQuertJson=IdcInfoUtil.getInstance().queryIdc(jsonObject);
            idcQuertJson.put("reply","idc_query_reply");
                socket.getOutputStream().write(idcQuertJson.toString().getBytes("UTF-8"));
        }else if(jsonObject.getString("ide").equals("企业客户")){
            JSONObject idcQuertJson=IdcInfoUtil.getInstance().queryIdc(jsonObject);

            idcQuertJson.put("reply","idc_query_reply");
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public IdcInfoEntity queryIdc2(JSONObject o) {//根据idcid 来查表idcinfo
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String idcid=o.getString("id");

        Query query=session.createQuery("select iie from IdcInfoEntity iie where iie.idcId=:idcid");
        query.setParameter("idcid",idcid);
        IdcInfoEntity entity=(IdcInfoEntity)query.uniqueResult();

        return entity;

    }
}
