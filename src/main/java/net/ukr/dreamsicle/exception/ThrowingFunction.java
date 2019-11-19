package net.ukr.dreamsicle.exception;

import java.util.function.Function;

import static java.util.Objects.requireNonNull;
import static net.ukr.dreamsicle.dao.imp.UserDaoImpl.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

@FunctionalInterface
public interface ThrowingFunction<T, R, E extends Exception> {


    static <T, R> Function<T, R> unchecked(final ThrowingFunction<T, R, ?> f) {
        return requireNonNull(f).uncheck();
    }

    R apply(T arg) throws E;

    default Function<T, R> uncheck() {
        return t -> {
            try {
                return apply(t);
            } catch (final Exception e) {
                throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
            }
        };
    }
}
