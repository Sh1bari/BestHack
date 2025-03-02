package ru.noxly.authorization.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.noxly.authorization.models.models.dto.UserDto;
import ru.noxly.authorization.models.models.requests.ValidateUserReq;
import ru.noxly.authorization.models.models.responses.RegisterUserDtoRes;
import ru.noxly.authorization.services.UserService;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("/internal")
@Tag(name = "Internal API", description = "")
public class InternalController {

    private final UserService userService;

    @Operation(summary = "Get user data (secured to services)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = RegisterUserDtoRes.class))
                    })
    })
    @PostMapping("/validate-user")
    public ResponseEntity<UserDto> registerUser(@RequestBody @Valid ValidateUserReq req) {
        val user = userService.validateUser(req.getToken());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }
}
