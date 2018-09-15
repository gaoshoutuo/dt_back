package Resource;

import DAO.CountDownDao;
import Entity.CountDownEntity;

import Entity.IdcInfoEntity;
import Entity.RegisterUserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class CountDownUtil implements CountDownDao {
    @Override
    public void add(CountDownEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(CountDownEntity entity) {

    }

    @Override
    public void delete(CountDownEntity entity) {

    }

    @Override
    public List<CountDownEntity> query() {
        return null;
    }


    //查询 某客户的最近的倒计时天数
    // 1 吧所有机房都建立条记录  添加机房里头会给这张表也加一个记录  对于做过维保的单位立马把值更新为90     删除机房还没有做
    // (查询 id<=flag   ups_ins  air_inspection  这两种)  ORDER BY cpbh asc  升级序  只选0-90 之间
    //  此表天数每次 -1  查询 当小于15天才会显示




    public JSONObject query22(Socket socket) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();

        List<CountDownEntity> llll=null;
        Query query=session.createQuery("select cde from CountDownEntity cde where cde.upsTime<15 OR cde.airTime<15");
        llll=(List<CountDownEntity>)query.getResultList();
        hibUtil.commitClose();
        if (llll==null){
            return null;
        }else{
            JSONObject object=new JSONObject();//客户id 客户姓名  客户机房 客户地区 ups剩余时间(天) 空调剩余时间(天)  dialog 中间也做一次  排序做一次 sql 上面的排序  还是代码上面的排序
            object.put("reply","count_down_reply");
            JSONArray ja=new JSONArray();
            for(int i=0;i<llll.size();i++){
                CountDownEntity count=llll.get(i);
                JSONObject obj=new JSONObject();
                obj.put("cus_id",count.getUserId());
                obj.put("cus_name",count.getUserName());
                obj.put("idc_id",count.getIdcId());
                obj.put("idc_location",count.getIdcLocation());
                obj.put("ups_time",count.getUpsTime()+"");
                obj.put("air_time",count.getAirTime()+"");
                System.out.println(count.getUpsTime()+"------"+count.getAirTime());
                ja.put(obj);
            }
            object.put("array",ja);
            try {
                socket.getOutputStream().write(object.toString().getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }

            return object;
        }
    }

    /**
     * 给我做测试用的 假如为报表里面没有机房数据则添加
     * idc id 查维保表  维保表为空则
     * @return
     */

    public void queryLost(String o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();


        Query query=session.createQuery("select cde from CountDownEntity cde where cde.idcId=:idcid");
        query.setParameter("idcid",o);
        CountDownEntity cde=(CountDownEntity)query.uniqueResult();
        hibUtil.commitClose();
        if (cde==null){//添加
            JSONObject object=new JSONObject();
            object.put("id",o);
            IdcInfoEntity ife=IdcInfoUtil.getInstance().queryIdc2(object);
           addCountDown(ife.getIdcId(),ife.getIdcLocationSimple(),ife.getUserId());
        }
       /* if (llll==null){
            return null;
        }else{
            JSONObject object=new JSONObject();//客户id 客户姓名  客户机房 客户地区 ups剩余时间(天) 空调剩余时间(天)  dialog 中间也做一次  排序做一次 sql 上面的排序  还是代码上面的排序
            object.put("reply","offline");
            JSONArray ja=new JSONArray();
            for(int i=0;i<llll.size();i++){
                CountDownEntity count=llll.get(i);
                JSONObject obj=new JSONObject();
                obj.put("cus_id",count.getUserId());
                obj.put("cus_name",count.getUserName());
                obj.put("idc_id",count.getIdcId());
                obj.put("idc_location",count.getIdcLocation());
                obj.put("ups_time",count.getUpsTime()+"");
                obj.put("air_time",count.getAirTime()+"");
                ja.put(obj);
            }
            object.put("array",ja);*/




    }



    public void updateAll() {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update CountDownEntity cde set cde.upsTime = cde.upsTime-1,cde.airTime=cde.airTime-1";
        Query query = session.createQuery(hql);

        int k=query.executeUpdate();
        System.out.println(k+"---------更新CDE成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public void updateSingleUps(String o) {//idcid 更新机房ups 巡检
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update CountDownEntity cde set cde.upsTime = 90 where cde.idcId=:idcid";
        Query query = session.createQuery(hql);
        query.setParameter("idcid",o);
        int k=query.executeUpdate();
        System.out.println(k+"---------更新CDE的ups成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public void updateSingleAir(String o) {//idcid 更新机房空调 巡检
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql = "update CountDownEntity cde set airTime=90 where cde.idcId=:idcid";
        Query query = session.createQuery(hql);
        query.setParameter("idcid",o);
        int k=query.executeUpdate();
        System.out.println(k+"---------更新CDE的air成功-----------");
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    public void addCountDown(String idcId,String idcLocation,String userId){
        CountDownUtil cdu=new CountDownUtil();
        CountDownEntity cde=new CountDownEntity();
        cde.setAirTime(90);
        cde.setUpsTime(90);
        cde.setIdcId(idcId);
        cde.setIdcLocation(idcLocation);
        cde.setUserId(userId);
        RegisterUserEntity rue1=TableRegUtil.getInstance().queryInID(userId);
        cde.setUserName(rue1.getName());
        cdu.add(cde);
    }

}
