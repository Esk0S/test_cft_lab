package ru.cft.test_cft.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IntervalRepository<U> extends CrudRepository<U, Integer> {
    U findMinInterval();
}
