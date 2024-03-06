package ru.cft.test_cft.app.mapper;

import ru.cft.test_cft.entities.IntervalLetter;

import java.util.Arrays;
import java.util.List;

public class IntervalLetterMapper implements IntervalMapper<List<String>, IntervalLetter> {
    @Override
    public List<String> toDomain(IntervalLetter entity) {
        return Arrays.asList(entity.getStart(), entity.getEnd());
    }

    @Override
    public IntervalLetter toEntity(List<String> domain) {
        return new IntervalLetter(domain.get(0), domain.get(1));
    }
}
