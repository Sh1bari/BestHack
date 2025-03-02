package ru.noxly.fuelseller.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.noxly.fuelseller.repositories.RepoResolver;

import java.time.OffsetDateTime;

import static ru.noxly.fuelseller.models.enums.LotStatus.NON_ACTIVE;

@Slf4j
@Service
@RequiredArgsConstructor
public class LotService {

    private final RepoResolver resolver;

    public Page<Lot> findAll(Specification<Lot> spec, Pageable pageable) {
        val fuels = resolver.resolve(Lot.class).findAll(spec, pageable);
        return fuels;
    }

    @Transactional
    public void runLotDateUpdater() {
        val lots = resolver.getLotRepository().findExpiredLots(OffsetDateTime.now());
        for (val lot : lots) {
            val entity = lot.toBuilder()
                    .setStatus(NON_ACTIVE)
                    .build();

            log.info("Lot with id {} expired, set status to {}", lot.getId(), NON_ACTIVE);
            resolver.resolve(Lot.class).save(entity);
        }
    }
}
