package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalLong;
import java.util.function.Function;

public class MaxSleepCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description = "Максимальная продолжительность сна (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalLong maxSleepingDuration = OptionalLong.of(sleepingSessions.stream()
                .mapToLong(SleepingSession::getSleepingSessionDuration)
                .max().orElse(0));
        return new SleepAnalysisResult(description, maxSleepingDuration.getAsLong());
    }
}