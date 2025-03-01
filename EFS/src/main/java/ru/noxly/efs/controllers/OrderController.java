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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.models.enums.DeliveryType;
import ru.noxly.efs.models.models.dto.LotDto;
import ru.noxly.efs.models.models.dto.OrderDto;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Order API", description = "")
public class OrderController {

    @Operation(summary = "Get order by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = OrderDto.class))
                    })
    })
    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        val order = OrderDto.init()
                .setId(1L)
                .setDate("2025-03-01")
                .setLotId(500L)
                .setKsssnb(123456L)
                .setKsssFuel(654321L)
                .setVolume(1500.75)
                .setDeliveryType(DeliveryType.SELF)
                .setClientId(UUID.randomUUID())
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(order);
    }
}
