package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InsomniaNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {
        Set<LocalDate> goodSleepDate = sleepingSessions.stream()
                .filter(session -> !session.getStartDate().equals(session.getFinishDate())
                        || session.getStartTime().isBefore(LocalTime.of(6, 0)))
                .map(SleepingSession::getStartDate)
                .collect(Collectors.toSet());
        long insomniaDate = sleepingSessions.stream()
                .filter(session -> !goodSleepDate.contains(session.getStartDate()))
                .count();
        return new SleepAnalysisResult("Количество беcсонных ночей за наблюдаемый период", insomniaDate);
    }
}
