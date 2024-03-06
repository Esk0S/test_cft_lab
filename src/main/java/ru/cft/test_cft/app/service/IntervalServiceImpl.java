package ru.cft.test_cft.app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.cft.test_cft.app.mapper.IntervalMapper;
import ru.cft.test_cft.entities.IntervalDigit;
import ru.cft.test_cft.entities.IntervalLetter;
import ru.cft.test_cft.repository.IntervalRepository;

import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IntervalServiceImpl implements IntervalService {
    private static final String EMPTY_INTERVAL = "Received empty interval";
    private static final String DIGIT_INTERVAL = "Received a digit interval ";
    private static final String LETTER_INTERVAL = "Received a letter interval ";
    private final IntervalRepository<IntervalDigit> intervalRepositoryDigit;
    private final IntervalRepository<IntervalLetter> intervalRepositoryLetter;
    @Qualifier("getDigitMapper")
    private final IntervalMapper<List<Integer>, IntervalDigit> digitMapper;
    @Qualifier("getLetterMapper")
    private final IntervalMapper<List<String>, IntervalLetter> letterMapper;

    @Override
    public List<List<Integer>> saveDigitIntervals(@NotNull List<List<?>> srcIntervals) {
        return processIntervals(srcIntervals, Integer.class, intervalRepositoryDigit, digitMapper, "Digit");
    }

    @Override
    public List<List<String>> saveLetterIntervals(@NotNull List<List<?>> srcIntervals) {
        return processIntervals(srcIntervals, String.class, intervalRepositoryLetter, letterMapper, "Letter");
    }

    @Override
    public List<String> getMinLetterInterval() {
        return getMinInterval(intervalRepositoryLetter, letterMapper, LETTER_INTERVAL);
    }

    @Override
    public List<Integer> getMinDigitInterval() {
        return getMinInterval(intervalRepositoryDigit, digitMapper, DIGIT_INTERVAL);
    }

    private <U, T extends Comparable<T>> List<T> getMinInterval(
            IntervalRepository<U> repository,
            IntervalMapper<List<T>, U> mapper,
            String intervalMsg
    ) {
        U interval;
        if ((interval = repository.findMinInterval()) == null) {
            log.debug(EMPTY_INTERVAL);
            return List.of();
        }
        List<T> intervalList = mapper.toDomain(interval);
        log.debug(() -> intervalMsg + intervalList);
        return intervalList;
    }

    private <U, T extends Comparable<T>> List<List<T>> processIntervals(
            List<List<?>> srcIntervals,
            Class<T> type,
            IntervalRepository<U> repository,
            IntervalMapper<List<T>, U> mapper,
            String typeLabel
    ) {
        List<List<T>> intervals = new ArrayList<>();
        for (var srcInterval : srcIntervals) {
            try {
                T start = type.cast(srcInterval.get(0));
                T end = type.cast(srcInterval.get(1));

                intervals.add(Arrays.asList(start, end));
            } catch (ClassCastException e) {
                log.warn(() -> typeLabel + " interval was not added " + srcInterval);
            }
        }
        List<List<T>> mergedIntervals = mergeIntervals(intervals);
        saveIntervals(mergedIntervals, repository, mapper, typeLabel);

        return mergedIntervals;
    }

    private <U, T extends Comparable<T>> void saveIntervals(
            List<List<T>> intervals,
            IntervalRepository<U> repository,
            IntervalMapper<List<T>, U> mapper,
            String typeLabel
    ) {
        for (var intervalData : intervals) {
            U interval = mapper.toEntity(intervalData);
            repository.save(interval);
            log.debug(() -> typeLabel + " interval added " + intervalData);
        }
    }

    private <T extends Comparable<T>> @NotNull List<List<T>> mergeIntervals(List<List<T>> intervalList) {
        if (intervalList == null || intervalList.isEmpty()) {
            return new ArrayList<>();
        }

        intervalList.sort(Comparator.comparing(interval -> interval.get(0)));

        List<List<T>> mergedIntervals = new ArrayList<>();
        List<T> currentInterval = intervalList.get(0);

        for (int i = 1; i < intervalList.size(); i++) {
            List<T> nextInterval = intervalList.get(i);

            if (currentInterval.get(1).compareTo(nextInterval.get(0)) >= 0) {
                ArrayList<T> curIntervalList = new ArrayList<>(List.of(currentInterval.get(1), nextInterval.get(1)));
                currentInterval.set(1, Collections.max(curIntervalList));
            } else {
                mergedIntervals.add(new ArrayList<>(currentInterval));
                currentInterval = nextInterval;
            }
        }

        mergedIntervals.add(new ArrayList<>(currentInterval));

        return mergedIntervals;
    }

}
