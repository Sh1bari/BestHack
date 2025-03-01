package ru.noxly.fuelseller.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.fuelseller.models.enums.LotStatus;
import ru.noxly.fuelseller.models.models.dto.LotDto;
import ru.noxly.fuelseller.models.models.dto.OilDepotDto;
import ru.noxly.fuelseller.models.models.requests.GetLotsReq;
import ru.noxly.fuelseller.models.models.requests.GetOilDepotsReq;
import ru.noxly.fuelseller.models.models.responses.LotPageRes;
import ru.noxly.fuelseller.models.models.responses.OilDepotPageRes;
import ru.noxly.fuelseller.services.LotService;
import ru.noxly.fuelseller.services.OilDepotService;
import ru.noxly.fuelseller.specifications.LotSpecifications;
import ru.noxly.fuelseller.specifications.OilDepotSpecifications;

import static ru.noxly.fuelseller.models.enums.LotStatus.ACCEPTED;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Lot API", description = "")
public class LotController {

    private final LotService lotService;

    private final ConversionService conversionService;

    @Operation(summary = "Get lots")
    @ApiResponses()
    @PostMapping("/lots")
    public ResponseEntity<LotPageRes> getFuels(@RequestBody GetLotsReq request) {
        val spec = Specification.where(LotSpecifications.withFilters(request));
        val lots = lotService.findAll(spec, request.getPageable().getPageable());
        val response = LotPageRes.fromPage(
                lots.map(fuel -> conversionService.convert(fuel, LotDto.class))
        );

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
