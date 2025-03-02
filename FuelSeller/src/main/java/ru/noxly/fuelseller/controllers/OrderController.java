package ru.noxly.fuelseller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.fuelseller.models.models.dto.LotDto;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;
import ru.noxly.fuelseller.models.models.dto.OrderDto;
import ru.noxly.fuelseller.models.models.requests.CreateOrderDto;
import ru.noxly.fuelseller.models.models.requests.GetOilDepotsReq;
import ru.noxly.fuelseller.models.models.requests.GetOrdersDto;
import ru.noxly.fuelseller.models.models.responses.LotPageRes;
import ru.noxly.fuelseller.models.models.responses.OilDepotPageRes;
import ru.noxly.fuelseller.models.models.responses.OrderPageRes;
import ru.noxly.fuelseller.repositories.OrderRepository;
import ru.noxly.fuelseller.services.OrderService;
import ru.noxly.fuelseller.specifications.OilDepotSpecifications;
import ru.noxly.fuelseller.specifications.OrderSpecifications;
import ru.noxly.validation.validation.annotations.BusValid;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Order API", description = "")
public class OrderController {

    private final OrderService orderService;

    private final ConversionService conversionService;

    @Operation(summary = "Create order")
    @ApiResponses()
    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@RequestBody @BusValid @Valid CreateOrderDto request) {
        val order = orderService.createOrder(request);
        val response = conversionService.convert(order, OrderDto.class);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    @Operation(summary = "Get Orders")
    @ApiResponses()
    @PostMapping("/orders/self")
    public ResponseEntity<OrderPageRes> getOrders(@RequestBody @Valid GetOrdersDto request) {
        val spec = Specification.where(OrderSpecifications.hasClientId(request.getUserId()));
        val orders = orderService.findAll(spec, request.getPageRequest().getPageable());
        val response = OrderPageRes.fromPage(
                orders.map(fuel -> conversionService.convert(fuel, OrderDto.class))
        );
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
