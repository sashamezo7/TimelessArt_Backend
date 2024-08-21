package repo;

import entity.AccountEntity;
import entity.ClientsEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientsRepo extends JpaRepository<ClientsEntity, Integer> {

    Optional<ClientsEntity> findByAccount(AccountEntity accountEntity);
}
