package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TypeOfCustomerTest {
    TypeOfCustomer counter = new TypeOfCustomer();
    long valueForEmptyList = 0;
    List<SleepingSession> sessions;

    @BeforeEach
    public void createSessionsList() {
        sessions = new ArrayList<>();
    }

    @Test
    void typeOfCustomerWith3OwlNightAnd3Pigeon() {
        sessions.add(SessionParser.parser("01.10.25 23:15;02.10.25 09:30;GOOD"));
        sessions.add(SessionParser.parser("02.10.25 23:50;03.10.25 09:40;BAD"));
        sessions.add(SessionParser.parser("03.10.25 23:50;04.10.25 09:40;BAD"));
        sessions.add(SessionParser.parser("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("06.10.25 13:30;06.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("07.10.25 13:30;07.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        String expectedValue = "Пользователь - сова";
        assertEquals(expectedValue, result.getDescription());
    }

    @Test
    void typeOfCustomerForNoSession() {
        SleepAnalysisResult result = counter.apply(sessions);
        assertEquals(valueForEmptyList, result.getValue());
    }

    @Test
    void typeOfCustomerWith3LarkNightAnd3Pigeon() {
        sessions.add(SessionParser.parser("01.10.25 21:15;02.10.25 06:30;GOOD"));
        sessions.add(SessionParser.parser("02.10.25 21:50;03.10.25 06:40;BAD"));
        sessions.add(SessionParser.parser("03.10.25 21:50;04.10.25 06:40;BAD"));
        sessions.add(SessionParser.parser("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("06.10.25 13:30;06.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("07.10.25 13:30;07.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        String expectedValue = "Пользователь - жаворонок";
        assertEquals(expectedValue, result.getDescription());
    }

    @Test
    void typeOfCustomerWith2LarkNight2OwlAnd3Pigeon() {
        sessions.add(SessionParser.parser("01.10.25 21:15;02.10.25 06:30;GOOD"));
        sessions.add(SessionParser.parser("02.10.25 21:50;03.10.25 06:40;BAD"));
        sessions.add(SessionParser.parser("03.10.25 23:50;04.10.25 09:40;BAD"));
        sessions.add(SessionParser.parser("04.10.25 23:50;05.10.25 09:40;BAD"));
        sessions.add(SessionParser.parser("05.10.25 13:30;05.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("06.10.25 13:30;06.10.25 14:15;NORMAL"));
        sessions.add(SessionParser.parser("07.10.25 13:30;07.10.25 14:15;NORMAL"));
        SleepAnalysisResult result = counter.apply(sessions);
        String expectedValue = "Пользователь - голубь";
        assertEquals(expectedValue, result.getDescription());
    }
}
