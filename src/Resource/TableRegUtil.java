package Resource;

import Bean.JsonBean;
import DAO.RegisterDao;
import Entity.RegisterUserEntity;
import Entity.Test3Entity;
import Entity.TesttableEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

public class TableRegUtil implements RegisterDao {
    private static TableRegUtil tu;
    public static TableRegUtil getInstance(){
        if (tu==null){
            tu=new TableRegUtil();
        }
        return tu;
    }
    @Override
    public void add(RegisterUserEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(RegisterUserEntity entity) {

    }

    @Override
    public void delete(RegisterUserEntity entity) {

    }

    @Override
    public List<RegisterUserEntity> query() {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select stu from RegisterUserEntity stu where stu.identity=:iden";
        Class isclass= RegisterUserEntity.class;
        //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        query.setParameter("iden","企业客户");
        List <RegisterUserEntity>users = query.getResultList();
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return users;
    }

    public RegisterUserEntity newIDQuery(String info) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select stu from RegisterUserEntity stu where stu.identity=:iden and stu.userId=:userid";
        Class isclass= RegisterUserEntity.class;
        //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        query.setParameter("iden","企业客户");
        query.setParameter("iden",info);
        RegisterUserEntity users = (RegisterUserEntity)query.uniqueResult();
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return users;
    }

    public RegisterUserEntity newNameQuery(String info) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select stu from RegisterUserEntity stu where stu.identity=:iden and stu.name=:name";
        Class isclass= RegisterUserEntity.class;
        //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        query.setParameter("iden","企业客户");
        query.setParameter("name",info);
        RegisterUserEntity users = (RegisterUserEntity)query.uniqueResult();
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return users;
    }

    public JSONObject query(JSONObject o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String userStr=o.getString("user_str");
        String pwdStr=o.getString("pwd_str");
        String hql2="select userId,pwd from RegisterUserEntity stu where stu.userId=?and stu.pwd=?";
     /*   RegisterUserEntity llll = (RegisterUserEntity)session.createQuery("select userId,pwd from RegisterUserEntity stu where stu.userId like :userid and stu.pwd like :pwd")
                .setParameter("userid", userStr).setParameter("pwd", pwdStr)
                .uniqueResult();*/
        RegisterUserEntity llll=null;
        Query query=session.createQuery("select stu from RegisterUserEntity stu where userId=:userid and pwd = :pwd");
        query.setParameter("userid",userStr);
        query.setParameter("pwd",pwdStr);
        llll=(RegisterUserEntity)query.uniqueResult();
        //System.out.println(users.size()+"----------------------------------");
        hibUtil.commitClose();
        if (llll!=null){
            System.out.println(llll.getCompany()+llll.getIdentity()+"--------------------------------------------");
            JSONObject loginReply=new JSONObject();
            loginReply.put("reply","login_reply");
            loginReply.put("identity",llll.getIdentity());
            loginReply.put("name",llll.getName());
            loginReply.put("userid",llll.getUserId());
            loginReply.put("location",llll.getLocation());
            loginReply.put("company",llll.getCompany());
           // loginReply.put("pwd",llll.getPwd()); 不必要
            return loginReply;
        }else{
            System.out.println("--------------------------------------------");
            return null;
        }
        //String hql="select stu from RegisterUserEntity stu";


        //  Query query = session.createQuery(hql);

        //List <RegisterUserEntity>users = query.getResultList();
        //查询出来的东西不能拿来做成实体


    }

    public RegisterUserEntity queryInID(String o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();

        List<RegisterUserEntity> llll=null;
        Query query=session.createQuery("select stu from RegisterUserEntity stu where userId=:userid");
        query.setParameter("userid",o);

        llll=(List<RegisterUserEntity>)query.getResultList();
        hibUtil.commitClose();
        if (llll!=null){
            return llll.get(0);
        }else{
            return null;
        }
        //System.out.println(users.size()+"----------------------------------");
    }

    public RegisterUserEntity checkQuery(RegisterUserEntity o) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        List<RegisterUserEntity> llll=null;
        Query query=session.createQuery("select stu from RegisterUserEntity stu where userId=:userid and pwd=:pwd");
        query.setParameter("userid",o.getUserId());
        query.setParameter("pwd",o.getPwd());

        llll=(List<RegisterUserEntity>)query.getResultList();
        hibUtil.commitClose();
        if (llll!=null){
            System.out.println(llll.get(0).getName()+"-----------------------------");
            return llll.get(0);
        }else{
            System.out.println("-----------------------------------------");
            return null;
        }
    }


    public void add(Test3Entity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

        //放弃 转移到infoupsAir
    public void queryMe(JSONObject jsonObject, Socket socket){
        TableRegUtil info=new TableRegUtil();RegisterUserEntity entity=null;
        if(jsonObject.getString("name").equals("007")){//按id 查找
            entity= info.queryInID(jsonObject.getString("id"));

        }else if(jsonObject.getString("id").equals("007")){//按姓名 查找
            entity= info.queryInID(jsonObject.getString("name"));
        }

        if(true){//假如那张表里面有数据则使用那张表的   每次新增机房  ups或者空调  add的时候修改 资产的时候都会修改这个数据  idc_id四项  user_id user_name upsnumber  airnumber  blank1 blank2

        }else{

        }


        if (entity!=null){//这种数据应该有一个触发机制  不用换后天去查找放在一个表里头
            String location=entity.getLocation();String company=entity.getCompany();String name=entity.getName();String userid=entity.getUserId();
            JSONObject json=new JSONObject();json.put("user_id",userid);
            JSONObject idcQueryJson=IdcInfoUtil.getInstance().queryIdc(json);//需要机房名称与类型
            JSONArray ja=idcQueryJson.getJSONArray("array");
            for(int i=0;i<ja.length();i++){//这张表不用json存储了  因为是机房对应的
                JSONObject obj=ja.getJSONObject(i);
                obj.getString("idc_id");
                obj.getString("idc_s_location");
                obj.getString("idcidc_name_id");
                obj.getString("idc_type");
            }


            try {
                socket.getOutputStream().write("112".getBytes("UTF-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
