package ru.noxly.fuelseller.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.repositories.RepoResolver;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotService {

    private final RepoResolver resolver;

    public Page<Lot> findAll(Specification<Lot> spec, Pageable pageable){
        val fuels = resolver.resolve(Lot.class).findAll(spec, pageable);
        return fuels;
    }
}
