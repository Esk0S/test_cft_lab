package ru.cft.test_cft.app.mapper;

public interface IntervalMapper<D, E> {
    D toDomain(E entity);

    E toEntity(D domain);
}
