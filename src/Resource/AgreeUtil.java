package Resource;

import DAO.AgreeDao;
import Entity.AgreeTableEntity;
import Entity.AssertEntity;
import Entity.RegisterUserEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.List;

public class AgreeUtil implements AgreeDao {

    private static AgreeUtil agreeUtil;
    public static AgreeUtil getInstance(){
        if (agreeUtil==null)agreeUtil=new AgreeUtil();
        return agreeUtil;
    }
    @Override
    public void add(AgreeTableEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(AgreeTableEntity entity) {

    }


    public void update(JSONObject o) {//写好立马测试
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
       // String isAgree=o.getString("is_agree");
        String isAgree=o.getString("im_agree");
        Timestamp timestamp=Timestamp.valueOf(o.getString("timestamp"));
        AgreeTableEntity llll=null;
        Query query=session.createQuery("update AgreeTableEntity stu set stu.isAgree=:isAgree  where dateHistory=:date ");
        query.setParameter("isAgree",isAgree);
        query.setParameter("date",timestamp);
        llll=(AgreeTableEntity)query.uniqueResult();
        System.out.println(llll.getIsAgree());

        hibUtil.commitClose();
    }

    @Override
    public void delete(AgreeTableEntity entity) {

    }

    @Override
    public List<AgreeTableEntity> query() {//后期拉报表用
        return null;
    }

    /**
     * 查询 最后一次客户id 及业务时间
     * 1 假如业务时间 超过及不存在记录 则off_use
     * 2 假如 小于则 on_use  并且返回 客户id
     * @param o
     * @return
     */


    public JSONObject query(JSONObject o) {//后期拉报表用
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String engId=o.getString("eng_id");
        String timestamp=o.getString("timestamp");
        List<AgreeTableEntity> llll=null;
        Query query=session.createQuery("select stu from AgreeTableEntity stu where engId=:engid");
        query.setParameter("engid",engId);

        llll=query.getResultList();
        AgreeTableEntity ate=llll.get(llll.size()-1);
        JSONObject jsonObj=new JSONObject();
        jsonObj.put("reply","help_reply");
        jsonObj.put("instruction","off_use");
        if (ate!=null){
            if (Timestamp.valueOf(timestamp).getTime()-ate.getDateHistory().getTime()<=36000000&&ate.getIsAgree().equals("我同意")){//十小时之内
                jsonObj.put("instruction","on_use");
                jsonObj.put("cus_id",ate.getCusId());
                jsonObj.put("work_type",ate.getWorkType());
            }else if(ate.getIsAgree().equals("我不同意")){
                jsonObj.put("instruction","refuse");
            }
        }
        return jsonObj;

    }

}
