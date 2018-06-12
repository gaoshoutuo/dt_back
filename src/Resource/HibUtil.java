package Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibUtil {

    public StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
            .configure()
            .build();
    public Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder()
            .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
    public SessionFactory sessionFactory=metadata.getSessionFactoryBuilder().build();
    private Session session;
    private Transaction transaction;

    public void openSession(){
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
    }
    public void commitT(){
        transaction.commit();
    }
    public void commitClose(){

        //9. 关闭会话
        session.close();
        //10. 关闭会话工厂
        sessionFactory.close();
    }
    public Session getSession(){
        return session;
    }
}
