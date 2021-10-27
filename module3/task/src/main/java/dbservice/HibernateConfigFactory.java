package dbservice;

import accounts.UserDataSet;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;

public class HibernateConfigFactory {
    public enum ConfigType {POSTGRES}

    public static Configuration get(ConfigType type) {
        return switch (type) {
            case POSTGRES -> getPostgresConfig();
        };
    }

    private static Configuration getPostgresConfig() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(UserDataSet.class);

        try {
            config.setProperties(readProperties());
            return config;
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return null;
    }

    private static Properties readProperties() throws IOException {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.put("hibernate.connection.username", "postgres");
        properties.put("hibernate.connection.password", "password");
        properties.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/postgres");
        properties.put("hibernate.hbm2ddl.auto", "update");
        return properties;
    }
}
