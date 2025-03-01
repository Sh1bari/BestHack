package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.models.enums.FuelType;
import ru.noxly.efs.models.models.dto.LotDto;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Lot API", description = "")
public class LotController {

    @Operation(summary = "Get lot by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = LotDto.class))
                    })
    })
    @GetMapping("/lot/{id}")
    public ResponseEntity<LotDto> getLotById(@PathVariable Long id) {
        val lot = LotDto.init()
                .setId(1L)
                .setFuelType(FuelType.AI_92)
                .setOilDepotName("Main Depot")
                .setOilDepotRegion("Central")
                .setRemainingFuel(1000.0)
                .setPricePerTon(500.0)
                .build();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(lot);
    }
}
