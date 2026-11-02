package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BadQualitySessionCounterTest {
    BadQualitySessionCounter counter = new BadQualitySessionCounter();
    long valueForEmptyList = 0;
    List<SleepingSession> sessions;

    @BeforeEach
    public void createSessionsList() {
        sessions = new ArrayList<>();
    }

    @Test
    void countMaxDurationFor3SessionsWith1Bad() {
        sessions.add(SessionParser.parser("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(SessionParser.parser("02.10.25 23:50;03.10.25 06:40;BAD"));
        sessions.add(SessionParser.parser("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(1, result.getValue());
    }

    @Test
    void countMaxDurationForNoSessions() {
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }

    @Test
    void countMaxDurationFor3GoodSession() {
        sessions.add(SessionParser.parser("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(SessionParser.parser("02.10.25 23:50;03.10.25 06:40;GOOD"));
        sessions.add(SessionParser.parser("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }
}
