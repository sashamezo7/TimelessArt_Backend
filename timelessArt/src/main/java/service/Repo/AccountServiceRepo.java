package service.Repo;

import entity.AccountEntity;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;

public interface AccountServiceRepo {
    @Transactional
    AccountEntity createAccount(String email, String password);

    @Transactional
    boolean deleteAccount(Long id);

    @Transactional
    String authenticate(String email, String password);

    @Transactional
    void requestPasswordReset(String email);

    @Transactional
    void resetPassword(String token, String newPassword);

    default String generateToken() {
        return UUID.randomUUID().toString();
    }

    @Transactional
    List<AccountEntity> getAllAccounts();
}
