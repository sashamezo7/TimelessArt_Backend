package service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import validation.EmailValidator;
import entity.AccountEntity;
import exception.InvalidCredentialsException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repo.AccountRepo;
import validation.PasswordValidator;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@ApplicationScoped
public class AccountService {

    @Inject
    private AccountRepo accountRepo;
    private static final String issuer = "http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui";
    private static final String audience = "http://localhost:8080/q/dev-ui/io.quarkus.quarkus-smallrye-openapi/swagger-ui";
    private static final long expirationTime = 3600000;
    private static final SecretKey key = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);
    @Transactional
    public AccountEntity createAccount(String email, String password) {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Email not valid");
        }
        if (!PasswordValidator.isValid(password)) {
            throw new IllegalArgumentException("Password should be at least 8 caracters long, contains at least one lowercase letter, and at least one uppercase letter");
        }
        String bcryptHashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        AccountEntity account = AccountEntity.builder()
                .email(email)
                .password(bcryptHashString)
                .token(generateToken())
                .validAccount(true)
                .build();
        accountRepo.save(account);
        return account;
    }

    @Transactional
    public boolean deleteAccount(Long id) {
        if (accountRepo.existsById(id)) {
            accountRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public String authenticate(String email, String password) {
        if (!EmailValidator.isValid(email)) {
            throw new IllegalArgumentException("Email not valid");
        }
        if (!PasswordValidator.isValid(password)) {
            throw new IllegalArgumentException("Password should be at least 8 caracters long, contains at least one lowercase letter, and at least one uppercase letter");
        }
        AccountEntity account = accountRepo.findByEmail(email);
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
        String jwt = generateJwtToken(account);

        Jws<Claims> claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt);

        if (!claims.getBody().getIssuer().equals(issuer) || !claims.getBody().getAudience().equals(audience)) {
            throw new InvalidCredentialsException("Invalid token");
        }

        return jwt;
    }
    private String generateJwtToken(AccountEntity account) {
        return Jwts.builder()
                .setSubject(account.getEmail())
                .claim("roles", List.of("ROLE_USER"))
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(key)
                .compact();
    }
    private String generateToken() {
        return UUID.randomUUID().toString();
    }
    private Boolean containsSpaces(String text) {
        for (char c : text.toCharArray()) {
            if (c == ' ') {
                return true;
            }
        }
        return false;
    }
}
