package net.ukr.dreamsicle.dao;

import net.ukr.dreamsicle.connection.ConnectionManager;
import net.ukr.dreamsicle.exception.ApplicationException;
import org.apache.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;
import java.util.function.Supplier;

import static net.ukr.dreamsicle.util.Constants.CONNECTION_CLOSE;
import static net.ukr.dreamsicle.util.Constants.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

public abstract class AbstractDao implements AutoCloseable {

    private static final Logger LOGGER = Logger.getLogger(AbstractDao.class);

    protected <RESULT> RESULT executeStatement(Supplier<String> query, Function<PreparedStatement, RESULT> functions) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query.get())) {
            return functions.apply(preparedStatement);
        } catch (SQLException e) {
            LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getStackTrace(), e);
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        LOGGER.info(CONNECTION_CLOSE);
    }
}
