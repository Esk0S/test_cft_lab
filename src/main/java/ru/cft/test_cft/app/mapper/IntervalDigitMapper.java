package ru.cft.test_cft.app.mapper;

import ru.cft.test_cft.entities.IntervalDigit;

import java.util.Arrays;
import java.util.List;

public class IntervalDigitMapper implements IntervalMapper<List<Integer>, IntervalDigit> {
    @Override
    public List<Integer> toDomain(IntervalDigit entity) {
        return Arrays.asList(entity.getStart(), entity.getEnd());
    }

    @Override
    public IntervalDigit toEntity(List<Integer> domain) {
        return new IntervalDigit(domain.get(0), domain.get(1));
    }
}
