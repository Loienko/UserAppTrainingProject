package net.ukr.dreamsicle.connection;


import net.ukr.dreamsicle.exception.ApplicationException;
import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import static net.ukr.dreamsicle.util.Constants.*;

public class ConnectionManager implements AutoCloseable {
    private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class);

    public static Connection getConnection() {
        try (InputStream input = ConnectionManager.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties properties = new Properties();
            properties.load(Optional.ofNullable(input).orElseThrow(() -> new ApplicationException(UNABLE_TO_FIND_CONFIG_PROPERTIES)));

            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(properties.getProperty("db.url"), properties.getProperty("db.username"), properties.getProperty("db.password"));
        } catch (SQLException | IOException e) {
            LOGGER.error(DATABASE_CONNECTION_ERROR + e.getMessage(), e);
            throw new ApplicationException(DATABASE_CONNECTION_ERROR, e);
        }
    }

    @Override
    public void close() {
        LOGGER.info(CONNECTION_CLOSE);
    }
}
