package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.noxly.efs.webClient.main.FuelClient;
import ru.noxly.efs.webClient.main.models.responses.FuelListRes;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Fuel API", description = "")
public class FuelController {

    private final FuelClient fuelClient;

    @Operation(summary = "Get fuels")
    @ApiResponses()
    @GetMapping("/fuels")
    public ResponseEntity<FuelListRes> getFuels() {
        val response = fuelClient.getFuels();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
