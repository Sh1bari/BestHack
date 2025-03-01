package ru.noxly.efs.webClient.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.efs.webClient.main.models.requests.GetLotsReq;
import ru.noxly.efs.webClient.main.models.requests.GetOilDepotsReq;
import ru.noxly.efs.webClient.main.models.responses.FuelListRes;
import ru.noxly.efs.webClient.main.models.responses.LotPageRes;
import ru.noxly.efs.webClient.main.models.responses.OilDepotPageRes;

@Slf4j
@Service
@RequiredArgsConstructor
public class FuelClient {

    private final FuelWebClient fuelWebClient;

    public FuelListRes getFuels() {
        val uri = "/api/fuels";
        var response = fuelWebClient.get(
                uri,
                FuelListRes.class
        );

        return response;
    }

    public OilDepotPageRes getOilDepots(final GetOilDepotsReq request) {
        val uri = "/api/oil-depots";
        var response = fuelWebClient.post(
                uri,
                request,
                OilDepotPageRes.class
        );

        return response;
    }

    public LotPageRes getLots(final GetLotsReq request) {
        val uri = "/api/lots";
        var response = fuelWebClient.post(
                uri,
                request,
                LotPageRes.class
        );

        return response;
    }
}
