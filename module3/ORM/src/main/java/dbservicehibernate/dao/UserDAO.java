package dbservicehibernate.dao;

import dbservicehibernate.datasets.UserDataSet;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UserDAO {
    private final Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public UserDataSet getUserById(long id) {
        return (UserDataSet) session.get(UserDataSet.class, id);
    }

    public UserDataSet getUserByName(String name) {
        Criteria criteria = session.createCriteria(UserDataSet.class);
        return (UserDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult();
    }

    public long addUser(String name) {
        return (long) session.save(new UserDataSet(name));
    }
}
