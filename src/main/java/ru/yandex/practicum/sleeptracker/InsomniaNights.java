package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class InsomniaNights implements Function<List<SleepingSession>, SleepAnalysisResult> {
    private final String description = "Количество беcсонных ночей за наблюдаемый период";

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessions) {

        double value = sleepingSessions.stream()//Написано со значительной помощью ИИ
                .map(SleepingSession::getStartDate)
                        .collect(Collectors.toSet())
                        .stream()
                       .filter(date -> {
                            boolean hasNightSleep = sleepingSessions.stream()
                                    .filter(session -> session.getStartDate().equals(date))
                                    .anyMatch(session -> {
                                        if (!session.getStartDate().equals(session.getFinishDate())) {
                                            return true;
                                        }
                                        LocalTime start = session.getStartTime();
                                        LocalTime finish = session.getFinishTime();
                                        LocalTime sixAm = LocalTime.of(6, 0);
                                        LocalTime midnight = LocalTime.of(0, 0);
                                        return start.isBefore(sixAm) && finish.isAfter(midnight);
                                    });

                            return !hasNightSleep;
                        })
                        .count();

        return new SleepAnalysisResult(description, value);
    }
}
