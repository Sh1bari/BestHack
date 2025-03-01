package ru.noxly.efs.webClient.main.models.responses;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ru.noxly.efs.common.PageResponse;
import ru.noxly.efs.models.models.dto.OilDepotDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class OilDepotPageRes {

    private final PageResponse<OilDepotDto> oilDepots; // Используем PageImpl вместо Page

    public static OilDepotPageRes fromPage(org.springframework.data.domain.Page<OilDepotDto> page) {
        return new OilDepotPageRes(PageResponse.fromPage(page));
    }
}
