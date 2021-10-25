package dbservicejdbc.dao;

import dbservicejdbc.datasets.User;
import dbservicejdbc.executor.Executor;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDAO {
    private final Executor executor;

    public UserDAO(Connection connection) {
        this.executor = new Executor(connection);
    }

    public void addUser(String name) throws SQLException {
        executor.execUpdate(String.format("insert into users (user_name) values ('%s')", name));
    }

    public User getUserByName(String name) throws SQLException {
        String query = String.format("select * from users where user_name = '%s'", name);
        return executor.execQuery(
                query,
                resultSet -> {
                    resultSet.next();
                    return new User(resultSet.getLong("id"), name);
                }
        );
    }

    public User getUserById(long id) throws SQLException {
        String query = String.format("select * from users where id = %s", id);
        return executor.execQuery(
                query,
                resultSet -> {
                    resultSet.next();
                    return new User(id, resultSet.getString("user_name"));
                }
        );
    }
}
