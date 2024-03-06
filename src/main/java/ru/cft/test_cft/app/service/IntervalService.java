package ru.cft.test_cft.app.service;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public interface IntervalService {
    List<List<Integer>> saveDigitIntervals(@NotNull List<List<?>> intervalList);

    List<List<String>> saveLetterIntervals(@NotNull List<List<?>> intervalList);

    List<Integer> getMinDigitInterval();

    List<String> getMinLetterInterval();
}
