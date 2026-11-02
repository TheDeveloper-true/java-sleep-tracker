package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private final String description;
    private final double value;

    SleepAnalysisResult(String description, double value) {
        this.description = description;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    @Override
    public String toString() {
        boolean isInteger = (value % 1) == 0;// проверка на long/double сделана с помощью ИИ
        String formattedValue;
        if (isInteger)
            formattedValue = String.valueOf((long) value);
        else
            formattedValue = String.valueOf(value);
        return description + ": " + formattedValue;
    }
}
