package ru.noxly.efs.webClient.main;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import ru.noxly.efs.models.models.dto.OrderDto;
import ru.noxly.efs.webClient.auth.models.UserDto;
import ru.noxly.efs.webClient.main.models.requests.*;
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

    public OrderDto createOrder(final UserDto userDto, final CreateOrderDtoReq request) {
        val uri = "/api/orders";
        val body = CreateOrderDto.init()
                .setLotId(request.getLotId())
                .setKsssnb(request.getKsssnb())
                .setKssFuel(request.getKssFuel())
                .setVolume(request.getVolume())
                .setDeliveryType(request.getDeliveryType())
                .setClient(
                        CreateClientDto.init()
                                .setId(userDto.getId())
                                .setMail(userDto.getMail())
                                .setName(userDto.getUserInfo().getName())
                                .setMiddleName(userDto.getUserInfo().getMiddleName())
                                .setSurname(userDto.getUserInfo().getSurname())
                                .build()
                )
                .build();
        val response = fuelWebClient.post(
                uri,
                body,
                OrderDto.class);
        return response;
    }
}
