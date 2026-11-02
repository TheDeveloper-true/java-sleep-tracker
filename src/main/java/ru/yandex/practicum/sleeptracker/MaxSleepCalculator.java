package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MaxSleepCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalLong maxSleepingDuration = sleepingSessions.stream()
                .mapToLong(SleepingSession::getSleepingSessionDuration)
                .max();
        long result = maxSleepingDuration.orElse((0));
        return new SleepAnalysisResult("Максимальная продолжительность сна (в минутах)", result);
    }
}