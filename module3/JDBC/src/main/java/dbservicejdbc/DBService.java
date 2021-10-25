package dbservicejdbc;

import dbservicejdbc.dao.DAOException;
import dbservicejdbc.dao.UserDAO;
import dbservicejdbc.datasets.User;

import java.sql.Connection;
import java.sql.SQLException;

public class DBService {
    private final Connection connection = ConnectionFactory.get(ConnectionFactory.ConnectionType.POSTGRES);
    private final UserDAO dao = new UserDAO(connection);

    public long addUser(String name) throws DAOException {
        try {
            connection.setAutoCommit(false);
            dao.addUser(name);
            connection.commit();
            return dao.getUserByName(name).getId();
        } catch (SQLException exc) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new DAOException(e);
            }
            throw new DAOException(exc);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException exc) {
                exc.printStackTrace();
            }
        }
    }

    public User getUserById(long id) throws DAOException {
        try {
            return dao.getUserById(id);
        } catch (SQLException exception) {
            exception.printStackTrace();
            throw new DAOException(exception);
        }
    }

    public void printConnectionInfo() {
        try {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
