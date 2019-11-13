package net.ukr.dreamsicle.connection;

import org.apache.log4j.Logger;
import org.postgresql.Driver;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory implements AutoCloseable {
    public static final String CONNECTION_IS_CLOSE = "Connection is close";
    private static final Logger LOGGER = Logger.getLogger(ConnectionFactory.class);

    private static String jdbcURL = "jdbc:postgresql://localhost:5432/postgres?useSSL=false";
    private static String jdbcUsername = "postgres";
    private static String jdbcPassword = "docker";

    public static java.sql.Connection getConnection() {
        try {
            Properties props = new Properties();
            props.setProperty("user", jdbcUsername);
            props.setProperty("password", jdbcPassword);
            DriverManager.registerDriver(new Driver());
            return DriverManager.getConnection(jdbcURL, props);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new RuntimeException();
        }
    }


    @Override
    public void close() throws Exception {
        LOGGER.info(CONNECTION_IS_CLOSE);
    }
}
