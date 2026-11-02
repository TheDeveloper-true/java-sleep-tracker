package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Программа запущена без аргументов");
            return;
        }

        List<Function<List<SleepingSession>, SleepAnalysisResult>> analyzers = new ArrayList<>();
        analyzers.add(new SleepingSessionSize());
        analyzers.add(new MinSleepCalculator());
        analyzers.add(new MaxSleepCalculator());
        analyzers.add(new AverageSleepCalculator());
        analyzers.add(new BadQualitySessionCounter());
        analyzers.add(new InsomniaNights());
        analyzers.add(new TypeOfCustomer());

        URL resource = SleepTrackerApp.class.getResource(args[0]);

        if (resource == null) {
            throw new IOException("Файл " + args[0] + " не найден в ресурсах!");
        }
        List<SleepingSession> sessions;
        try (InputStream inputStream = resource.openStream(); BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            sessions = reader.lines()
                    .map(SessionParser::parser)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (sessions.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            analyzers
                    .forEach(analyzer -> {
                        SleepAnalysisResult result = analyzer.apply(sessions);
                        if (result.getDescription().equals("Количество сессий с плохим качеством сна: ")) {
                            if (result.getValue() == 0) {
                                System.out.println("В наблюдаемый период времени вы спали хорошо");
                            } else {
                                System.out.println(result);
                            }
                        } else if (result.getDescription().contains("Пользователь -")) {
                            System.out.println(result.getDescription());
                        } else
                            System.out.println(result);
                    });
        }
    }
}