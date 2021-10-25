package dbservicehibernate;

import dbservicehibernate.dao.UserDAO;
import dbservicehibernate.datasets.UserDataSet;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class DBService {
    private final SessionFactory sessionFactory;

    public DBService() {
        Configuration config = HibernateConfigFactory.get(HibernateConfigFactory.ConfigType.POSTGRES);
        this.sessionFactory = createSessionFactory(config);
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry registry = builder.build();
        return configuration.buildSessionFactory(registry);
    }

    public long addUser(String name) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        long id = dao.addUser(name);
        transaction.commit();
        session.close();
        return id;
    }

    public UserDataSet getUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        UserDataSet user = dao.getUserById(id);
        transaction.commit();
        session.close();
        return user;
    }
}
