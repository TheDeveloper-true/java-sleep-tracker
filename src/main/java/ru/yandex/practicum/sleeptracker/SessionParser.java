package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SessionParser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy");

    public static SleepingSession parser(String stringFromFile) {
        String[] parts = stringFromFile.split(";");
        String[] startParts = parts[0].split(" ");
        String[] finishParts = parts[1].split(" ");
        LocalDate startDate = LocalDate.parse(startParts[0], FORMATTER);
        LocalTime startTime = LocalTime.parse(startParts[1]);
        LocalDate finishDate = LocalDate.parse(finishParts[0], FORMATTER);
        LocalTime finishTime = LocalTime.parse(finishParts[1]);
        SleepQuality quality = SleepQuality.valueOf(parts[2]);
        return new SleepingSession(startDate, finishDate, startTime, finishTime, quality);
    }
}
