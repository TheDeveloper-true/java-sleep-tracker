package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;

public class TypeOfCustomer implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sleepingSessionList) {
        String description;
        long result = 0;
        List<SleepingSession> owlNightsList = sleepingSessionList.stream()
                .filter(session -> session.getStartTime().isAfter(LocalTime.of(23, 0)) &&
                        session.getFinishTime().isAfter(LocalTime.of(9, 0)))
                .toList();
        List<SleepingSession> larkNightsList = sleepingSessionList.stream()
                .filter(session -> session.getStartTime().isBefore(LocalTime.of(22, 0)) &&
                        session.getFinishTime().isBefore(LocalTime.of(7, 0)))
                .toList();
        if (owlNightsList.size() > larkNightsList.size()) {
            description = "Пользователь - сова";
        } else if (owlNightsList.size() < larkNightsList.size()) {
            description = "Пользователь - жаворонок";
        } else {
            description = "Пользователь - голубь";
        }
        return new SleepAnalysisResult(description, result);
    }
}
