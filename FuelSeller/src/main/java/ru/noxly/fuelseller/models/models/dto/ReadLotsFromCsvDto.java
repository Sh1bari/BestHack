package ru.noxly.fuelseller.models.models.dto;

import lombok.*;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class ReadLotsFromCsvDto {

    private final List<LotFromCsvDto> lots;

    private final String filename;
}
