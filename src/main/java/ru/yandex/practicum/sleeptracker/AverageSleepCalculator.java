package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Function;

public class AverageSleepCalculator implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        OptionalDouble averageDuration = sleepingSessions.stream()
                .mapToLong(SleepingSession::getSleepingSessionDuration)
                .average();
        double result = averageDuration.orElse(0.0);
        return new SleepAnalysisResult("Средняя продолжительность сна (в минутах)", result);
    }
}
