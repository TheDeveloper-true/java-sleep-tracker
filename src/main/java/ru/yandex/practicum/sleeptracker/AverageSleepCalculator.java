package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageSleepCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description = "Средняя продолжительность сна (в минутах)";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalDouble averageDuration = OptionalDouble.of(sleepingSessions.stream()
                .mapToLong(SleepingSession::getSleepingSessionDuration)
                .average().orElse(0.0));
        return new SleepAnalysisResult(description, averageDuration.getAsDouble());
    }
}
