package dbservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public enum ConnectionType {POSTGRES}

    public static Connection get(ConnectionType type) {
        return switch (type) {
            case POSTGRES -> getPostgresConnection();
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
}
