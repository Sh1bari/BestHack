package ru.noxly.fuelseller.repositories;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.entites.*;
import ru.sh1bari.resolver.RepoResolverHelper;
import ru.sh1bari.resolver.RepositoryWrapper;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class RepoResolver {

    //Repositories
    private final FuelRepository fuelRepository;
    private final LotRepository lotRepository;
    private final OilDepotRepository oilDepotRepository;
    private final CsvFileRepository csvFileRepository;
    private final SchedulerRepository schedulerRepository;
    private final ClientRepository clientRepository;
    private final OrderRepository orderRepository;

    @PostConstruct
    private void init() {
        resolver.put(Fuel.class, fuelRepository);
        resolver.put(Lot.class, lotRepository);
        resolver.put(OilDepot.class, oilDepotRepository);
        resolver.put(CsvFile.class, csvFileRepository);
        resolver.put(Scheduler.class, schedulerRepository);
        resolver.put(Client.class, clientRepository);
        resolver.put(Order.class, orderRepository);
    }

    private final Map<Class<?>, JpaRepository<?, ?>> resolver = new HashMap<>();
    private final RepoResolverHelper repoResolverHelper;

    public <T, ID> RepositoryWrapper<T, ID> resolve(Class<T> entityClass) {
        return repoResolverHelper.resolve(entityClass, resolver);
    }
}
