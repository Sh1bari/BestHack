package ru.noxly.efs.webClient.main.models.responses;

import lombok.*;
import ru.noxly.efs.common.PageResponse;
import ru.noxly.efs.models.models.dto.LotDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class LotPageRes {

    private final PageResponse<LotDto> lots;

    public static LotPageRes fromPage(org.springframework.data.domain.Page<LotDto> page) {
        return new LotPageRes(PageResponse.fromPage(page));
    }
}
