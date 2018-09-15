package Resource;

import DAO.UpsAirDao;
import Entity.FixMessageEntity;
import Entity.IdcInfoEntity;
import Entity.InfoUpsAirEntity;
import Entity.UpsAirAnyEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class UpsAirUtil implements UpsAirDao {

    private static UpsAirUtil upa;
    public static UpsAirUtil getInstance(){
        if (upa==null){
            upa=new UpsAirUtil();
        }
        return upa;
    }
    @Override
    public void add(UpsAirAnyEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
        System.out.println("UpsAirAnyEntity添加成功");
    }

    @Override
    public void update(UpsAirAnyEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update UpsAirAnyEntity uae set uae.jsonData = :jsondata where uae.idcId= :idcid and uae.blank1=:blank1";
        Query query = session.createQuery(hql);
        query.setParameter("blank1",entity.getBlank1());//就是ups1 ups2 这些玩意
        query.setParameter("jsondata",entity.getJsonData());
        query.setParameter("idcid",entity.getIdcId());
        int k=query.executeUpdate();
        // session.getTransaction().commit();
        // session.close();
        // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------更新UAE成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void delete(UpsAirAnyEntity entity) {

    }

    @Override
    public List<UpsAirAnyEntity> query(UpsAirAnyEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select uae.jsonData from UpsAirAnyEntity uae where uae.idcId= :idcid and uae.deviceType=:type";
        Query query=session.createQuery(hql);
        query.setParameter("idcid",entity.getIdcId());
        query.setParameter("type",entity.getDeviceType());
        List<UpsAirAnyEntity>list=query.getResultList();
        System.out.println(list.size()+"----------uae查询成功-------");
        hibUtil.commitClose();
        return list;
    }

    public List<UpsAirAnyEntity> queryType(JSONObject obj) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select uae.jsonData from UpsAirAnyEntity uae where uae.cusId= :idcid and uae.deviceType=:type";
        Query query=session.createQuery(hql);
        //query.setParameter("idcid",obj.getIdcId());
        //query.setParameter("type",obj.getDeviceType());
        List<UpsAirAnyEntity>list=query.getResultList();
        System.out.println(list.size()+"----------uae查询成功-------");
        hibUtil.commitClose();
        return list;
    }

        //根据idcid u以及ups1 这种来查唯一数据  注释写个自己看
    public UpsAirAnyEntity query2(UpsAirAnyEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select uae.jsonData from UpsAirAnyEntity uae where uae.idcId= :idcid and uae.blank1=:blank1";
        Query query=session.createQuery(hql);
        query.setParameter("idcid",entity.getIdcId());
        query.setParameter("blank1",entity.getBlank1());//按ups1 ups2 这样查找
        UpsAirAnyEntity upsAirAnyEntity=(UpsAirAnyEntity)query.uniqueResult();
        if (upsAirAnyEntity!=null)
        System.out.println(upsAirAnyEntity.getIdcId()+"----------uae查询成功-------");
        hibUtil.commitClose();
        return upsAirAnyEntity;
    }



    public UpsAirAnyEntity methidIn(JSONObject jsonObject){
        UpsAirAnyEntity uae=new UpsAirAnyEntity();
        //JSONObject asset=jsonObject.getJSONObject("asset");
        JSONObject asset=new JSONObject(jsonObject.getString("asset"));
        System.out.println("----------123444-------");
        String deviceNumId=asset.getString("device_num_id");
        String idcId=jsonObject.getString("idcid");
        String type=jsonObject.getString("type");

        uae.setJsonData(asset.toString());
        uae.setBlank1(deviceNumId);
        uae.setIdcId(idcId);
        uae.setDeviceType(type);
        uae.setCusId("123");
       return uae;
    }

    public void qua(UpsAirAnyEntity uae){
        UpsAirAnyEntity upsAirAnyEntity=UpsAirUtil.getInstance().query2(uae);
        System.out.println("----------123-------");
        if(upsAirAnyEntity!=null){
            update(uae);
        }else{
            add(uae);
            //1 infoupsair 为空的话  则添加一条数据  假如是有数据的话 根据type原数据＋1
            JSONObject obj=new JSONObject();
            obj.put("id",uae.getIdcId());
            InfoUpsAirEntity iuae=InfoUpsAirUtil.getInstance().queryNumber(obj);//这边添加资产的话 那idcid 查找
            if (iuae==null){//add  idcid  userid username
                InfoUpsAirEntity iuaentity=new InfoUpsAirEntity();
                iuaentity.setIdcId(uae.getIdcId());
                IdcInfoEntity ife=IdcInfoUtil.getInstance().queryIdc2(obj);


                iuaentity.setIdcLocationSimple(ife.getIdcLocationSimple());
                iuaentity.setIdcName(ife.getIdcName());
                iuaentity.setIdcType(ife.getIdcType());

                iuaentity.setUserId(ife.getUserId());
                iuaentity.setUserName(ife.getUserName());
                if (uae.getDeviceType().equals("ups")){
                    iuaentity.setUpsNumber(1);
                    iuaentity.setAirNumber(0);
                }else if(uae.getDeviceType().equals("air")){
                    iuaentity.setUpsNumber(0);
                    iuaentity.setAirNumber(1);
                }
                iuaentity.setBlank1("0");//标志位
                //idcid 查userid username
                InfoUpsAirUtil.getInstance().add(iuaentity);
            }else{
                if (uae.getDeviceType().equals("ups")){
                    InfoUpsAirUtil.getInstance().updateUps(obj);
                }else if(uae.getDeviceType().equals("air")){
                    InfoUpsAirUtil.getInstance().updateAir(obj);
                }
            }
        }
    }

    public void lastMe(JSONObject jsonObject){
        System.out.println("----------123123-------");
        UpsAirAnyEntity upsAirAnyEntity=methidIn(jsonObject);
        System.out.println("----------123123-------");
        qua(upsAirAnyEntity);
    }


    //做检修的时候查询 则显示几个  ups1 ups2
    public String fixMe(JSONObject jsonObject){//不做了 直接找对应

            UpsAirAnyEntity entity=methidIn(jsonObject);
            JSONObject json=new JSONObject();
            jsonObject.put("reply","device_num_id");
            List<UpsAirAnyEntity> list=query(entity);
            jsonObject.put("num",list.size());
            //socket.getOutputStream().write(json.toString().getBytes("UTF-8"));
       return jsonObject.toString();
    }



  /*  @Override
    public List<UpsAirAnyEntity> query(JSONObject jsonObject) {





        return null;
    }*/
}
