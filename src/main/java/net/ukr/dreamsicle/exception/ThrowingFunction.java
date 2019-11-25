package net.ukr.dreamsicle.exception;

import org.apache.log4j.Logger;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {

    Logger LOGGER = Logger.getLogger(ThrowingFunction.class);
    String PROBLEM_OF_WORKING_WITH_THE_DATABASE = "Sorry, problem of working with the database.\t";

    static <T, R> Function<T, R> unchecked(final ThrowingFunction<T, R, ?> f) {
        return requireNonNull(f).uncheck();
    }

    R apply(T arg) throws E;

    default Function<T, R> uncheck() {
        return t -> {
            try {
                return apply(t);
            } catch (final Exception e) {
                LOGGER.error(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
                throw new DreamsicleException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e, 503);
            }
        };
    }
}
