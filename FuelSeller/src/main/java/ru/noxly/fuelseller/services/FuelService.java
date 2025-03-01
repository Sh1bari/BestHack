package ru.noxly.fuelseller.services;

import lombok.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.entites.Fuel;
import ru.noxly.fuelseller.repositories.RepoResolver;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FuelService {

    private final RepoResolver resolver;

    public List<Fuel> findAll(){
        val fuels = resolver.resolve(Fuel.class).findAll(Specification.where(null));
        return fuels;
    }
}
