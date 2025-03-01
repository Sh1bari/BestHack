package ru.noxly.fuelseller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;
import ru.noxly.fuelseller.models.models.requests.GetOilDepotsReq;
import ru.noxly.fuelseller.models.models.responses.OilDepotPageRes;
import ru.noxly.fuelseller.services.OilDepotService;
import ru.noxly.fuelseller.specifications.OilDepotSpecifications;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Oil depot API", description = "")
public class OilDepotController {

    private final OilDepotService oilDepotService;

    private final ConversionService conversionService;

    @Operation(summary = "Get oil depots")
    @ApiResponses()
    @PostMapping("/oil-depots")
    public ResponseEntity<OilDepotPageRes> getFuels(@RequestBody GetOilDepotsReq request) {
        val spec = Specification.where(OilDepotSpecifications.hasNameLikeIgnoreCase(request.getName()))
                .and(OilDepotSpecifications.hasRegionLikeIgnoreCase(request.getRegion()));
        val oilDepots = oilDepotService.findAll(spec, request.getPageable().getPageable());
        val response = OilDepotPageRes.fromPage(
                oilDepots.map(fuel -> conversionService.convert(fuel, OilDepotDto.class))
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
