package service.Repo;

import entity.AccountEntity;
import jakarta.annotation.PostConstruct;

public interface JwtServiceRepo {
    @PostConstruct
    void init() throws Exception;

    String generateJwtToken(AccountEntity account);
}
