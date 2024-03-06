package ru.cft.test_cft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cft.test_cft.entities.IntervalDigit;

@Repository
public interface IntervalRepositoryDigit extends IntervalRepository<IntervalDigit> {
    @Query("""
            from IntervalDigit
            where (id, start, end) IN (select min(id), min(start), min(end) from IntervalDigit)
              """)
    @Override
    IntervalDigit findMinInterval();
}
