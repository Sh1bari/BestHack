package ru.noxly.fuelseller.models.models.responses;

import lombok.*;
import ru.noxly.fuelseller.common.PageResponse;
import ru.noxly.fuelseller.models.entites.Order;
import ru.noxly.fuelseller.models.models.dto.LotDto;
import ru.noxly.fuelseller.models.models.dto.OrderDto;

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
