package repo;


import entity.AccountEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountRepo extends JpaRepository<AccountEntity,Long> {
    AccountEntity findByEmail(String email);

    @Query("SELECT a FROM AccountEntity a WHERE a.token = :token")
    AccountEntity findByToken(@Param("token") String token);
    @Query("SELECT a FROM AccountEntity a WHERE a.validAccount = true")
    List<AccountEntity> findByValidAccountTrue();
    @Query("SELECT a FROM AccountEntity a WHERE a.validAccount = false")
    List<AccountEntity> findByValidAccountFalse();


    @Modifying
    @Transactional
    @Query("UPDATE AccountEntity a SET a.validAccount = :isValid WHERE a.id = :id")
    void updateAccountValidity(@Param("id") Long id, @Param("isValid") boolean isValid);

}
