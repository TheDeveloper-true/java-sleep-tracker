package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MinSleepCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalLong minSleepingDuration = sleepingSessions.stream()
                .mapToLong(SleepingSession::getSleepingSessionDuration)
                .min();
        long result = minSleepingDuration.orElse(0);
        return new SleepAnalysisResult("Минимальная продолжительность сна (в минутах)", result);
    }
}
