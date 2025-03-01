package ru.noxly.authorization.repositories;

import ru.noxly.authorization.models.entities.UserInfo;
import ru.sh1bari.resolver.BaseJpaRepository;

public interface UserInfoRepository extends BaseJpaRepository<UserInfo, Long> {
}
