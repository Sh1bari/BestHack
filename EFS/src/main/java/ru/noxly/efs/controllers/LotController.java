package ru.noxly.efs.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.efs.models.enums.LotStatus;
import ru.noxly.efs.models.models.dto.LotDto;
import ru.noxly.efs.webClient.main.FuelClient;
import ru.noxly.efs.webClient.main.models.requests.GetLotsReq;
import ru.noxly.efs.webClient.main.models.responses.LotPageRes;

import java.util.Set;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Lot API", description = "")
public class LotController {

    private final FuelClient fuelClient;

    @Operation(summary = "Get lots")
    @ApiResponses()
    @PostMapping("/lots")
    public ResponseEntity<LotPageRes> getLotById(@RequestBody GetLotsReq request,
                                                 @AuthenticationPrincipal UserDetails user) {
        if (user.getAuthorities().stream()
                .noneMatch(authority -> authority.getAuthority().equals("ROLE_ADMIN"))) {
            request = request.toBuilder()
                    .setLotStatus(Set.of(LotStatus.ACCEPTED))
                    .build();
        }
        val response = fuelClient.getLots(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}
