package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinSleepCalculatorTest {
    MinSleepCalculator counter = new MinSleepCalculator();
    long valueForEmptyList = 0;
    List<SleepingSession> sessions;

    @BeforeEach
    public void createSessionsList() {
        sessions = new ArrayList<>();
    }

    @Test
    void countMinDurationFor3Sessions() {
        sessions.add(new SleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;NORMAL"));
        sessions.add(new SleepingSession("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(45, result.getValue());
    }

    @Test
    void countMinDurationForNoSessions() {
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }
}
