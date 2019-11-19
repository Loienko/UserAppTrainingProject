package net.ukr.dreamsicle.dao;

import net.ukr.dreamsicle.connection.ConnectionManager;
import net.ukr.dreamsicle.exception.ApplicationException;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractDao implements AutoCloseable {

    private static final String PROBLEM_OF_WORKING_WITH_THE_DATABASE = "Sorry, problem of working with the database.\t";

    protected <RESULT> RESULT executeStatement(Supplier<String> query, Function<PreparedStatement, RESULT> functions) {
        try (var connection = ConnectionManager.getConnection();
             var preparedStatement = connection.prepareStatement(query.get())) {
            return functions.apply(preparedStatement);
        } catch (SQLException e) {
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    @Override
    public void close() {
        // TODO ADD log later
    }
}
