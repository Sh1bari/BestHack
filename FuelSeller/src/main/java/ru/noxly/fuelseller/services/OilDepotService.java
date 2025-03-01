package ru.noxly.fuelseller.services;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.models.entites.OilDepot;
import ru.noxly.fuelseller.repositories.RepoResolver;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OilDepotService {

    private final RepoResolver resolver;

    public Page<OilDepot> findAll(Specification<OilDepot> spec, Pageable pageable){
        val fuels = resolver.resolve(OilDepot.class).findAll(spec, pageable);
        return fuels;
    }
}
