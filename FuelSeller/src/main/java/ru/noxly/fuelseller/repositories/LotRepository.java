package ru.noxly.fuelseller.repositories;

import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import ru.noxly.fuelseller.models.entites.Lot;
import ru.sh1bari.resolver.BaseJpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

public interface LotRepository extends BaseJpaRepository<Lot, Long> {

    @Query("SELECT l FROM Lot l WHERE l.date < :now AND l.status <> 'ACCEPTED'")
    List<Lot> findExpiredLots(@Param("now") OffsetDateTime now);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
            @QueryHint(name = "jakarta.persistence.lock.timeout", value = "5000") // Таймаут 5 секунд
    })
    @Query("SELECT l FROM Lot l WHERE l.id = :id")
    Optional<Lot> findByIdForUpdate(@Param("id") Long id);

}
