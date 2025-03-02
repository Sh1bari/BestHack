package ru.noxly.authorization.repositories;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.noxly.authorization.models.entities.User;
import ru.noxly.authorization.models.entities.UserInfo;
import ru.sh1bari.resolver.RepoResolverHelper;
import ru.sh1bari.resolver.RepositoryWrapper;

import java.util.HashMap;
import java.util.Map;

@Service
@Getter
@RequiredArgsConstructor
public class RepoResolver {

    //Repositories
    private final UserRepository userRepo;
    private final UserInfoRepository userInfoRepo;
    private final Map<Class<?>, JpaRepository<?, ?>> resolver = new HashMap<>();
    private final RepoResolverHelper repoResolverHelper;

    @PostConstruct
    private void init() {
        resolver.put(User.class, userRepo);
        resolver.put(UserInfo.class, userInfoRepo);
    }

    public <T, ID> RepositoryWrapper<T, ID> resolve(Class<T> entityClass) {
        return repoResolverHelper.resolve(entityClass, resolver);
    }
}
