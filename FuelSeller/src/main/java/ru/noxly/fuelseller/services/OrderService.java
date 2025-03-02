package ru.noxly.fuelseller.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.fuelseller.exceptions.GeneralException;
import ru.noxly.fuelseller.models.entites.*;
import ru.noxly.fuelseller.models.enums.LotStatus;
import ru.noxly.fuelseller.models.models.requests.CreateOrderDto;
import ru.noxly.fuelseller.repositories.RepoResolver;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final RepoResolver resolver;

    private final ConversionService conversionService;

    @Transactional
    public Order createOrder(final CreateOrderDto request) {
        val client = resolver.resolve(Client.class).findById(request.getClient().getId());
        val fuel = resolver.resolve(Fuel.class).findById(request.getKssFuel());
        val oilDepot = resolver.resolve(OilDepot.class).findById(request.getKsssnb());
        val lot = resolver.getLotRepository().findByIdForUpdate(request.getLotId())
                .orElseThrow(()-> new GeneralException(404, "Lot not found"));

        if(lot.getStatus() != LotStatus.ACCEPTED){
            throw new GeneralException(409, "Lot is not accepted");
        }
        if(lot.getRemainingFuel() - request.getVolume() < 0){
            throw new GeneralException(409, "Can't sell more fuel then available");
        }
        val lotEntity = lot.toBuilder()
                .setRemainingFuel(lot.getRemainingFuel() - request.getVolume())
                .build();
        resolver.resolve(Lot.class).save(lotEntity);

        val order = conversionService.convert(request, Order.class);

        val entity = requireNonNull(order).toBuilder()
                .setLot(lot)
                .setClient(client)
                .setFuel(fuel)
                .setOilDepot(oilDepot)
                .build();

        return resolver.resolve(Order.class).save(entity);
    }
}
