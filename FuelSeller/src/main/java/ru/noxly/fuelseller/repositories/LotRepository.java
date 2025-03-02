package ru.noxly.fuelseller.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.sh1bari.resolver.BaseJpaRepository;

import java.time.OffsetDateTime;
import java.util.List;

public interface LotRepository extends BaseJpaRepository<Lot, Long> {

    @Query("SELECT l FROM Lot l WHERE l.date < :now AND l.status <> 'ACCEPTED'")
    List<Lot> findExpiredLots(@Param("now") OffsetDateTime now);


}
