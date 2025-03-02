package ru.noxly.authorization.repositories;

import ru.noxly.authorization.models.entities.User;
import ru.sh1bari.resolver.BaseJpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseJpaRepository<User, UUID> {
    boolean existsByUsername(String username);

    boolean existsByMail(String email);

    Optional<User> findByMail(String mail);
}
