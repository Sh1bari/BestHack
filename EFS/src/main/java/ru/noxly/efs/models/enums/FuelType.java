package ru.noxly.efs.models.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FuelType {
    AI_92("АИ-92"),
    AI_95("АИ-95"),
    AI_92_ECTO("АИ-92 Экто"),
    AI_95_ECTO("АИ-95 Экто"),
    DT("ДТ");

    private final String name;
}
