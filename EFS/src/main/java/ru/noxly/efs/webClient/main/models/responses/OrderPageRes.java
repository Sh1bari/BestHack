package ru.noxly.efs.webClient.main.models.responses;

import lombok.*;
import ru.noxly.efs.common.PageResponse;
import ru.noxly.efs.models.models.dto.OrderDto;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class OrderPageRes {

    private final PageResponse<OrderDto> orders;

    public static OrderPageRes fromPage(org.springframework.data.domain.Page<OrderDto> page) {
        return new OrderPageRes(PageResponse.fromPage(page));
    }
}
