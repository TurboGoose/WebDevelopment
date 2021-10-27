package dbservice.dao;

import accounts.UserDataSet;
import org.hibernate.NonUniqueObjectException;
import org.hibernate.Session;

public class UserDAO {
    private final Session session;

    public UserDAO(Session session) {
        this.session = session;
    }

    public boolean addUser(String login, String password) {
        try {
            session.save(new UserDataSet(login, password));
            return true;
        } catch (NonUniqueObjectException exc) {
            return false;
        }
    }

    public UserDataSet getUserByLogin(String login) {
        return (UserDataSet) session.get(UserDataSet.class, login);
    }

    public void deleteUserByLogin(String login) {
        session.delete(getUserByLogin(login));
    }
}
