package net.ukr.dreamsicle.connection;


import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements AutoCloseable {
    public static final String CONNECTION_IS_CLOSE = "Connection is close";
    public static final String UNABLE_TO_FIND_CONFIG_PROPERTIES = "Sorry, unable to find config.properties";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            if (input == null) {
                LOGGER.error(UNABLE_TO_FIND_CONFIG_PROPERTIES);
                return null;
            }
            properties.load(input);

            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
            throw new RuntimeException();
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info(CONNECTION_IS_CLOSE);
    }
}
