package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.webClient.main.FuelClient;
import ru.noxly.efs.webClient.main.models.requests.GetOilDepotsReq;
import ru.noxly.efs.webClient.main.models.responses.OilDepotPageRes;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Oil depot API", description = "")
public class OilDepotController {

    private final FuelClient fuelClient;

    @Operation(summary = "Get oil depots")
    @ApiResponses()
    @PostMapping("/oil-depots")
    public ResponseEntity<OilDepotPageRes> getFuels(@RequestBody GetOilDepotsReq request) {
        val response = fuelClient.getOilDepots(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
