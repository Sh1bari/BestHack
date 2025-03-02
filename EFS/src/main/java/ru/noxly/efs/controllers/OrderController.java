package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.models.enums.DeliveryType;
import ru.noxly.efs.models.models.dto.LotDto;
import ru.noxly.efs.models.models.dto.OrderDto;
import ru.noxly.efs.security.CustomUserDetails;
import ru.noxly.efs.utils.Formatter;
import ru.noxly.efs.webClient.main.FuelClient;
import ru.noxly.efs.webClient.main.models.requests.CreateOrderDto;
import ru.noxly.efs.webClient.main.models.requests.CreateOrderDtoReq;

import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.util.UUID;

import static java.time.OffsetTime.now;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Order API", description = "")
public class OrderController {

    private final FuelClient fuelClient;

    @Operation(summary = "Create order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @Secured("ROLE_USER")
    @PostMapping("/orders")
    public ResponseEntity<OrderDto> createOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                @RequestBody CreateOrderDtoReq request){
        val response = fuelClient.createOrder(customUserDetails.getUser(), request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
