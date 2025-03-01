package ru.noxly.fuelseller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.noxly.fuelseller.models.models.dto.FuelDto;
import ru.noxly.fuelseller.models.models.responses.FuelListRes;
import ru.noxly.fuelseller.services.FuelService;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Fuel API", description = "")
public class FuelController {

    private final FuelService fuelService;

    private final ConversionService conversionService;

    @Operation(summary = "Get fuels")
    @ApiResponses()
    @GetMapping("/fuels")
    public ResponseEntity<FuelListRes> getFuels() {
        val fuels = fuelService.findAll();
        val response = FuelListRes.init()
                .setFuels(
                        fuels.stream()
                                .map(fuel -> conversionService.convert(fuel, FuelDto.class))
                                .toList()
                )
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
