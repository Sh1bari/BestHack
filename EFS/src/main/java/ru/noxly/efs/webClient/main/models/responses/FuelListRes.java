package ru.noxly.efs.webClient.main.models.responses;

import lombok.*;
import ru.noxly.efs.models.models.dto.FuelDto;

import java.util.List;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class FuelListRes {

    private final List<FuelDto> fuels;
}
