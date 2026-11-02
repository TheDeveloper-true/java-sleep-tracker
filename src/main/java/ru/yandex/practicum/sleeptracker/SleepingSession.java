package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final LocalTime startTime;
    private final LocalTime finishTime;
    private final SleepQuality quality;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");

    public SleepingSession(String strFromFile) {
        String[] parts = strFromFile.split(";");
        String startPart = parts[0];
        String finishPart = parts[1];
        String[] startParts = startPart.split(" ");
        String[] finishParts = finishPart.split(" ");
        this.startDate = LocalDate.parse(startParts[0], formatter);
        this.startTime = LocalTime.parse(startParts[1]);
        this.finishDate = LocalDate.parse(finishParts[0], formatter);
        this.finishTime = LocalTime.parse(finishParts[1]);
        this.quality = SleepQuality.valueOf(parts[2]);
    }

    public long getSleepingSessionDuration() {
        Duration duration = Duration.between(startTime, finishTime);
        if (duration.isNegative())
            duration = duration.plusDays(1);
        return duration.toMinutes();
    }

    public SleepQuality getQuality() {
        return quality;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }
}
