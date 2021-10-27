package dbservice;

import accounts.UserDataSet;
import dbservice.dao.UserDAO;
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

    public boolean addUser(String login, String password) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        boolean success = dao.addUser(login, password);
        transaction.commit();
        session.close();
        return success;
    }

    public UserDataSet getUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        UserDataSet user = dao.getUserByLogin(login);
        transaction.commit();
        session.close();
        return user;
    }

    public void deleteUserByLogin(String login) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        UserDAO dao = new UserDAO(session);
        dao.deleteUserByLogin(login);
        transaction.commit();
        session.close();
    }
}
