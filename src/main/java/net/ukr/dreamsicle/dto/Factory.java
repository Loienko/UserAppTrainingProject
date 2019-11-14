package net.ukr.dreamsicle.dto;

import java.util.List;

public interface Factory<T, R> {

    R toDto(T model);

    T fromDto(R model);

    List<R> findAll(List<T> model);
}
