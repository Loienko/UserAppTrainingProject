package net.ukr.dreamsicle.connection;


import net.ukr.dreamsicle.exception.MyOwnException;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

public class ConnectionFactory implements AutoCloseable {
    public static final String CONNECTION_IS_CLOSE = "Connection is close";
    public static final String UNABLE_TO_FIND_CONFIG_PROPERTIES = "Sorry, unable to find application.properties";
    public static final String DATABASE_CONNECTION_ERROR = "Database connection error";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    public static Connection getConnection() {
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(Optional.ofNullable(input).orElseThrow(() -> new MyOwnException(UNABLE_TO_FIND_CONFIG_PROPERTIES)));

            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (SQLException | IOException e) {
            LOGGER.error(e);
            throw new MyOwnException(DATABASE_CONNECTION_ERROR, e);
        }
    }

    @Override
    public void close() throws Exception {
        LOGGER.info(CONNECTION_IS_CLOSE);
    }
}
