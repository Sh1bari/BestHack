package ru.noxly.fuelseller.models.models.responses;

import lombok.*;
import ru.noxly.fuelseller.common.PageResponse;
import ru.noxly.fuelseller.models.models.dto.LotDto;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;

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
