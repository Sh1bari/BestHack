package ru.noxly.efs.webClient.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.efs.webClient.auth.models.UserDto;
import ru.noxly.efs.webClient.auth.models.requests.ValidateUserReq;
import ru.noxly.efs.webClient.main.models.FuelListRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuelClient {

    private final FuelWebClient fuelWebClient;

    public FuelListRes getFuels() {
        val uri = "/api/fuels";
        var response = fuelWebClient.get(uri,
                FuelListRes.class);

        return response;
    }
}
