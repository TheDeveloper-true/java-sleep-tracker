package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsomniaNightsTest {
    InsomniaNights counter = new InsomniaNights();
    long valueForEmptyList = 0;
    List<SleepingSession> sessions;

    @BeforeEach
    public void createSessionsList() {
        sessions = new ArrayList<>();
    }

    @Test
    void countInsomniaFor3SessionWith1Insomnia() {
        sessions.add(new SleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;BAD"));
        sessions.add(new SleepingSession("13.10.25 07:00;13.10.25 10:00;BAD"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(1, result.getValue());
    }

    @Test
    void countInsomniaFor3SessionWithoutInsomnia() {
        sessions.add(new SleepingSession("01.10.25 23:15;02.10.25 07:30;GOOD"));
        sessions.add(new SleepingSession("02.10.25 23:50;03.10.25 06:40;BAD"));
        sessions.add(new SleepingSession("12.10.25 23:00;13.10.25 05:00;BAD"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }

    @Test
    void countInsomniaForEmptyList() {
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }

    @Test
    void countInsomniaForDifferentMonth() {
        sessions.add(new SleepingSession("31.10.25 23:15;01.11.25 07:30;GOOD"));
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }
}
