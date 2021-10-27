package dbservice.dao;

import dbservice.HibernateConfigFactory;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TestUserDAO {
    public static UserDAO dao;
    public SessionFactory sessionFactory;
    {
        Configuration configuration = HibernateConfigFactory.get(HibernateConfigFactory.ConfigType.POSTGRES);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry registry = builder.build();
        sessionFactory = configuration.buildSessionFactory(registry);
    }

    @BeforeEach
    public void createUserDAO() {
        dao = new UserDAO(sessionFactory.openSession());
    }

    @Test
    public void whenAddUniqueUsersThenReturnTrue() {
        assertTrue(dao.addUser("user1", "password"));
        assertTrue(dao.addUser("user2", "password"));
    }

    @Test
    public void whenAddDuplicatedUsersThenReturnFalse() {
        assertTrue(dao.addUser("user", "password"));
        assertFalse(dao.addUser("user", "password"));
        assertFalse(dao.addUser("user", "other_password"));
    }
}