package net.ukr.dreamsicle.exception;

import java.util.function.Consumer;

import static net.ukr.dreamsicle.dao.imp.UserDaoImpl.PROBLEM_OF_WORKING_WITH_THE_DATABASE;

@FunctionalInterface
public interface ThrowingConsumer<T> extends Consumer<T> {

    @Override
   default void accept(final T elem) {
        try {
            acceptThrows(elem);
        } catch (final Exception e) {
            throw new ApplicationException(PROBLEM_OF_WORKING_WITH_THE_DATABASE + e.getMessage(), e);
        }
    }

    void acceptThrows(T elem) throws Exception;
}
