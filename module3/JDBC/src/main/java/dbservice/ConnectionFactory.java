package dbservice;

import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public enum ConnectionType {POSTGRES, H2}

    public static Connection get(ConnectionType type) {
        return switch (type) {
            case POSTGRES -> getPostgresConnection();
            case H2 -> getH2Connection();
        };
    }

    private static Connection getPostgresConnection() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?user=postgres&password=password");
        } catch (SQLException exc) {
            exc.printStackTrace();
        }
        return null;
    }

    private static Connection getH2Connection() {
        try {
            String url = "jdbc:h2:./h2db";
            String name = "tully";
            String pass = "tully";

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            return DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
