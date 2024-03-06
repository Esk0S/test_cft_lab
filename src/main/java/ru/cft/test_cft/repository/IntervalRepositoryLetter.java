package ru.cft.test_cft.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.cft.test_cft.entities.IntervalLetter;

@Repository
public interface IntervalRepositoryLetter extends IntervalRepository<IntervalLetter> {
    @Query("""
            from IntervalLetter
            where (id, start, end) IN (select min(id), min(start), min(end) from IntervalLetter)
              """)
    @Override
    IntervalLetter findMinInterval();
}
