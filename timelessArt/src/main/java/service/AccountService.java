package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import service.JwtService;
import validation.EmailValidator;
import entity.AccountEntity;
import exception.InvalidCredentialsException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repo.AccountRepo;
import validation.PasswordValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;



@ApplicationScoped
public class AccountService implements service.Repo.AccountServiceRepo {

    @Autowired
    AccountRepo accountRepo;

    @Inject
    JwtService jwtService;
    @Inject
    EmailService emailService;

    @Override
    @Transactional
    public AccountEntity createAccount(String email, String password) {

        accountRepo.findByEmail(email).ifPresent(existingAccount -> {
            throw new IllegalArgumentException("User already exists");
        });

        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Email not valid");
        }

        if (!PasswordValidator.isValid(password)) {
            throw new IllegalArgumentException("Password should be at least 8 characters long, contain at least one lowercase letter, and at least one uppercase letter");
        }

        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        AccountEntity account = AccountEntity.builder()
                .email(email)
                .password(bcryptHashString)
                .validAccount(true)
                .role(AccountEntity.Role.CLIENT)
                .build();
        accountRepo.save(account);

        return account;
    }


    @Override
    @Transactional
    public boolean deleteAccount(Long id) {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public String authenticate(String email, String password) {
        if (!EmailValidator.isValid(email)) {
            throw new InvalidCredentialsException("Email not valid");
        }
        if (!PasswordValidator.isValid(password)) {
            throw new InvalidCredentialsException("Password should be at least 8 caracters long, contains at least one lowercase letter, and at least one uppercase letter");
        }
        AccountEntity account = accountRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Account not found"));;
        if (account == null) {
            throw new InvalidCredentialsException("Invalid email, password");
        }

        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), account.getPassword());
        if (!result.verified) {
            accountRepo.updateAccountValidity(account.getId(), false);
            throw new InvalidCredentialsException("Invalid email, password");
        }
        if (!account.isValidAccount()) {
            throw new InvalidCredentialsException("Account is not verified");
        }
        return jwtService.generateJwtToken(account);
    }

    @Override
    @Transactional
    public void requestPasswordReset(String email) {
        AccountEntity account = accountRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Account not found"));
                ;
        if (account == null) {
            throw new IllegalArgumentException("Email not found");
        }
        String token = generateToken();
        account.setToken(token);
        account.setResetPasswordExpires(LocalDateTime.now().plusHours(1));
        accountRepo.save(account);
        String resetLink = "http://localhost:5173/reset-password?token=" + token;

        emailService.sendPasswordResetEmail(email, resetLink);
    }



    @Override
    @Transactional
    public void resetPassword(String token, String newPassword) {
        AccountEntity account = accountRepo.findByToken(token);
        if (account == null || account.getResetPasswordExpires().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Invalid or expired token");
        }

        account.setPassword(BCrypt.withDefaults().hashToString(12, newPassword.toCharArray()));
        account.setToken(null);
        account.setResetPasswordExpires(null);
        accountRepo.save(account);
    }


    @Override
    @Transactional
    public List<AccountEntity> getAllAccounts() {
        return accountRepo.findAll();
    }

}
