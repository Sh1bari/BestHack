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
import ru.noxly.authorization.models.models.requests.RegisterUserDtoReq;
import ru.noxly.authorization.models.models.responses.JwtTokenDtoRes;
import ru.noxly.authorization.models.models.responses.RegisterUserDtoRes;
import ru.noxly.authorization.services.AuthService;
import ru.noxly.validation.validation.annotations.BusValid;

import static ru.noxly.authorization.utils.JwtUtil.generateAccessToken;
import static ru.noxly.authorization.utils.JwtUtil.generateRefreshToken;

@RestController
@RequiredArgsConstructor
@Validated
@CrossOrigin
@SecurityRequirement(name = "bearerAuth")
@RequestMapping("")
@Tag(name = "Authorization API", description = "")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Register")
    @ApiResponses()
    @PostMapping("/register")
    public ResponseEntity<JwtTokenDtoRes> registerUser(@RequestBody @BusValid @Valid RegisterUserDtoReq req) {
        val user = authService.registerUser(req);
        val response = JwtTokenDtoRes.init()
                .setAccess(generateAccessToken(user))
                .setRefresh(generateRefreshToken(user))
                .build();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }

    /*@Operation(summary = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtTokenDtoRes.class))
                    })
    })
    @PostMapping("/login")
    public ResponseEntity<RegisterUserDtoRes> login(@RequestBody @Valid LoginDto req) {
        RegisterUserDtoRes res = authService.login(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    @Operation(summary = "Init user, registration in future")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtTokenDtoRes.class))
                    })
    })
    @PostMapping("/init-user")
    public ResponseEntity<?> init(@RequestBody @Valid CreateUserDtoReq req) {
        authService.init(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Refresh token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = JwtTokenDtoRes.class))
                    })
    })
    @PostMapping("/refresh")
    public ResponseEntity<JwtTokenDtoRes> refresh(@RequestBody @Valid RefreshDto req) {
        JwtTokenDtoRes res = authService.refresh(req);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }*/
}
