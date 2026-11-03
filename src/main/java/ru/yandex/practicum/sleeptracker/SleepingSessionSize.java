package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class SleepingSessionSize implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description = "Количество сессий сна";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        return new SleepAnalysisResult(description, sleepingSessions.size());
    }
}
