package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class SleepingSession {
    private final LocalDate startDate;
    private final LocalDate finishDate;
    private final LocalTime startTime;
    private final LocalTime finishTime;
    private final SleepQuality quality;

    public SleepingSession(LocalDate startDate, LocalDate finishDate, LocalTime startTime, LocalTime finishTime,
                           SleepQuality quality) {
        this.startDate = startDate;
        this.startTime = startTime;
        this.finishDate = finishDate;
        this.finishTime = finishTime;
        this.quality = quality;
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
