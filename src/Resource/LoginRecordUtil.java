package Resource;

import DAO.LoginRecordDAO;
import Entity.LoginRecordEntity;
import Entity.RegisterUserEntity;
import Entity.TesttableEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class LoginRecordUtil implements LoginRecordDAO {
    @Override
    public void add(LoginRecordEntity entity) {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        session.save(entity);
        hibUtil.commitT();
        hibUtil.commitClose();
    }

    @Override
    public void update(LoginRecordEntity entity) {

    }

    @Override
    public void delete(LoginRecordEntity entity) {

    }

    @Override
    public List<LoginRecordEntity> query() {
        HibUtil hibUtil= new HibUtil();
        hibUtil.openSession();
        Session session=hibUtil.getSession();
        String hql="select stu from RegisterUserEntity stu";
        Class isclass= TesttableEntity.class;
        //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        List <LoginRecordEntity>users = query.getResultList();
        //查询出来的东西不能拿来做成实体
        hibUtil.commitClose();
        return users;
    }
}
