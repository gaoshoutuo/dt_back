package Resource;

import DAO.RegisterDao;
import Entity.RegisterUserEntity;
import Entity.Test3Entity;
import Entity.TesttableEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class TableRegUtil implements RegisterDao {
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
        String hql="select stu from RegisterUserEntity stu";
        Class isclass= TesttableEntity.class;
        //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        List <RegisterUserEntity>users = query.getResultList();
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return users;
    }

    public void add(Test3Entity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }
}
