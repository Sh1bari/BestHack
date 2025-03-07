package ru.noxly.websocket.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class Formatter {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");

    public static OffsetDateTime parseDate(String value) {
        try {
            return value.isBlank() ? null : OffsetDateTime.parse(value.trim(), formatter);
        } catch (Exception e) {
            return null; // Пропускаем ошибки парсинга
        }
    }
}
