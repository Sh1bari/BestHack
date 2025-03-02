package ru.noxly.fuelseller.repositories;

import lombok.*;
import ru.noxly.fuelseller.models.entites.Client;
import ru.sh1bari.resolver.BaseJpaRepository;

import java.util.UUID;

public interface ClientRepository extends BaseJpaRepository<Client, Long> {

    boolean existsById(final UUID id);
}
