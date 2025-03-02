package ru.noxly.authorization.models.models.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Builder(builderMethodName = "init", setterPrefix = "set", toBuilder = true)
public class LoginDtoReq {
    @NotBlank
    private final String mail;
    @NotBlank
    private final String password;
}
