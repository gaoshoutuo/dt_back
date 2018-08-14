package Resource;

import DAO.IdcAssetDao;
import Entity.IdcAssetEntity;
import Entity.LoginRecordEntity;
import Entity.TesttableEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.util.List;

public class IdcAssetUtil implements IdcAssetDao {
    private static IdcAssetUtil tu;
    public static IdcAssetUtil getInstance(){
        if (tu==null){
            tu=new IdcAssetUtil();
        }
        return tu;
    }

    public IdcAssetEntity makeInitInfo(JSONObject jsonObject){
        IdcAssetEntity entity=new IdcAssetEntity();
        entity.setIdcId(jsonObject.getString("idc_id"));
        entity.setIdcType( jsonObject.getString("idc_type"));
        entity.setIdcLocationSimple(jsonObject.getString("idc_location_simple"));
        entity.setUserId(jsonObject.getString("user_id"));
        return entity;
    }

    @Override
    public void add(IdcAssetEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(IdcAssetEntity entity) {

    }

    public void updateSubAsset(IdcAssetEntity entity,int type){
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();//什么时候才有事务操作呢
        //Transaction tx = session.beginTransaction();
        switch (type){
            case 0:
                updateUps(session,entity.getIdcId(),entity.getUpsAsset());
                hibUtil.commitT();
                hibUtil.commitClose();
                //session.update();
               // hibUtil.commitT();
                //hibUtil.commitClose();
                break;

            case 1:
                updateAir(session,entity.getIdcId(),entity.getAirAssit());
                hibUtil.commitT();
                hibUtil.commitClose();

                break;

            case 2:
                updateEmi(session,entity.getIdcId(),entity.getEmiAssit());
                hibUtil.commitT();
                hibUtil.commitClose();

                break;

            case 3:
                updateMs(session,entity.getIdcId(),entity.getMonSoftAssit());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

            case 4:
                updateMi(session,entity.getIdcId(),entity.getMonSoftInterface());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

            case 5:
                updateMh(session,entity.getIdcId(),entity.getMonSoftHardware());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

            case 6:
                updateMac(session,entity.getIdcId(),entity.getMonSoftAc());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

            case 7:
                updateMv(session,entity.getIdcId(),entity.getMonVideo());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

            case 8:
                updateCab(session,entity.getIdcId(),entity.getMonCabient());
                hibUtil.commitT();
                hibUtil.commitClose();
                break;

                default:
                    break;
        }


    }

//ups
    private void updateUps(Session session,String idcId,String upsAs){
        //String hql = "update IdcAssetEntity iae set iae.upsAsset = :upsAsset where iae.idcId= :idcid";
        String hql = "update IdcAssetEntity iae set iae.upsAsset = :upsAsset where iae.idcId= :idcid";
        Query query = session.createQuery(hql);
        query.setParameter("upsAsset",upsAs);
        query.setParameter("idcid",idcId);
        int k=query.executeUpdate();
       // session.getTransaction().commit();
       // session.close();
       // System.out.println(idcId+"---------添加ups成功-----------"+upsAs); ok 一个功能成了
        System.out.println(k+"---------添加ups成功-----------");
    }

    //air
    private void updateAir(Session session,String idcId,String airAs){
        String hql = "update IdcAssetEntity iae set iae.airAssit = :airAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("airAsset",airAs);
        query.setParameter("idcId",idcId);
        int k=query.executeUpdate();
        System.out.println(k+"---------添加air成功-----------");
    }
    //emi 新风

    private void updateEmi(Session session,String idcId,String emiAs){
        String hql = "update IdcAssetEntity iae set iae.emiAssit = :emiAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("emiAsset",emiAs);
        query.setParameter("idcId",idcId);
        int k=query.executeUpdate();
        System.out.println(k+"---------添加emi成功-----------");
    }

    // mon_soft 监控软件

    private void updateMs(Session session,String idcId,String msAs){
        String hql = "update IdcAssetEntity iae set iae.monSoftAssit = :msAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("msAsset",msAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加监控软件成功-----------");
    }

    // mon_interface 监控接口
    private void updateMi(Session session,String idcId,String miAs){
        String hql = "update IdcAssetEntity iae set iae.monSoftInterface = :miAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("miAsset",miAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加监控接口成功-----------");
    }

    // mon_hard 监控硬件
    private void updateMh(Session session,String idcId,String mhAs){
        String hql = "update IdcAssetEntity iae set iae.monSoftHardware = :mhAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("mhAsset",mhAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加监控硬件成功-----------");
    }

    // mon_ac 门禁
    private void updateMac(Session session,String idcId,String macAs){
        String hql = "update IdcAssetEntity iae set iae.monSoftAc = :macAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("macAsset",macAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加门禁成功-----------");
    }

    // select_assit_mon_video 监控视频
    private void updateMv(Session session,String idcId,String mvAs){
        String hql = "update IdcAssetEntity iae set iae.monVideo = :mvAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("mvAsset",mvAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加监控视频成功-----------");
    }

    // select_assit_cabient  机柜
    private void updateCab(Session session,String idcId,String cabAs){
        String hql = "update IdcAssetEntity iae set iae.monCabient = :cabAsset where iae.idcId= :idcId";
        Query query = session.createQuery(hql);
        query.setParameter("cabAsset",cabAs);
        query.setParameter("idcId",idcId);
        query.executeUpdate();
        System.out.println("---------添加机柜成功-----------");
    }



    @Override
    public void delete(IdcAssetEntity entity) {

    }

    @Override
    public List<IdcAssetEntity> query() {
        return null;
    }

    public String queryIDC(String type,String idcId){
        String queryJson=null;
        switch (type){
            case "sub_ups":
                queryJson=upsQuery(idcId);
                break;
            case "sub_air":

                break;
            case "sub_emi":

                break;

            case "sub_ms":

                break;

            case "sub_mi":

                break;

            case "sub_mh":

                break;

            case "sub_mac":

                break;

            case "sub_mv":

                break;

            case "sub_cab":

                break;

                default:break;
        }

        return queryJson;
    }
    //upsquery
    private String upsQuery(String idcId){
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select  iae.upsAsset from IdcAssetEntity iae where iae.idcId=:idcid";
        Query query=session.createQuery(hql);
        query.setParameter("idcid",idcId);
       String upsAsset=(String)query.uniqueResult();
       System.out.println(upsAsset+"----------ups查询成功-------");
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return upsAsset;
    }

    //airquery
    private String airQuery(String idcId){
        return null;
    }

    //emiquery
    private String emiQuery(String idcId){
        return null;
    }

    //msquery
    private String msQuery(String idcId){
        return null;
    }

    //miquery
    private String miQuery(String idcId){
        return null;
    }

    //mhquery
    private String mhQuery(String idcId){
        return null;
    }

    //macquery
    private String macQuery(String idcId){
        return null;
    }

    //mvquery
    private String mvQuery(String idcId){
        return null;
    }

    //cabquery
    private String cabQuery(String idcId){
        return null;
    }
}
