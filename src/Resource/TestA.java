package Resource;

import DAO.TestAI;
import Entity.TesttableEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestA implements TestAI {


    /**
     * 本质是一个orm 用来管理
     */
    public   StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    public  Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
            .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
    public   SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
    private  Session session;
    private  Transaction transaction;

    @Test
    public  void  test(){
        StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
        SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(new TesttableEntity("54","nihao"));
        System.out.println("test....");
        transaction.commit();
        //9. 关闭会话
        session.close();
        //10. 关闭会话工厂
        sessionFactory.close();
    }
private void openSession(){
    session = sessionFactory.openSession();
    transaction = session.beginTransaction();
}
private void commitClose(){
    transaction.commit();
    //9. 关闭会话
    session.close();
    //10. 关闭会话工厂
    sessionFactory.close();
}
@Test
    @Override
    public void add(TesttableEntity entity) {
      openSession();
        session.save(entity);
        commitClose();
    }
    @Test
    public void addnew(TesttableEntity entity) {
        openSession();
        session.save(new TesttableEntity("21","tangeche"));
        commitClose();

    }

    @Override
    public void update(TesttableEntity entity) {

    }

    @Override
    public void delete(TesttableEntity entity) {

    }


    public List<TesttableEntity> query(String hql,Class isclass) {
        openSession();

        Query query = session.createQuery(hql,isclass);
        List users = query.list();
        commitClose();
        return users;
    }
    @Override
    public List<TesttableEntity> query() {
        openSession();
        String hql="select stu from TesttableEntity stu";
        Class isclass=TesttableEntity.class;
        System.out.println("...............................................1");
      //  Query query = session.createQuery(hql);
        Query query=session.createQuery(hql,isclass);
        System.out.println("...............................................2");
        List <TesttableEntity>users = query.getResultList();
        TesttableEntity testtableEntity =users.get(users.size()-1);

        //查询出来的东西不能拿来做成实体
        System.out.println(testtableEntity.getAge()+testtableEntity.getName());
        for(TesttableEntity e:users){
            System.out.println(e.getAge()+e.getName());
        }
        commitClose();
        return users;
    }

}
