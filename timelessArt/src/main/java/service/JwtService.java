package service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class JwtService {

    private final String issuer;
    private final String audience;
    private final long expirationTime;
    private final String secretKey;
    private SecretKey key;

    @Inject
    public JwtService(@ConfigProperty(name = "mp.jwt.verify.issuer") String issuer,
                      @ConfigProperty(name = "mp.jwt.verify.audience") String audience,
                      @ConfigProperty(name = "quarkus.jwt.expiration") long expirationTime,
                      @ConfigProperty(name = "quarkus.jwt.secret") String secretKey) {
        this.issuer = issuer;
        this.audience = audience;
        this.expirationTime = expirationTime;
        this.secretKey = secretKey;
    }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateJwtToken(String email, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000)) // Convert to milliseconds
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Claims verifyJwtToken(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(key)
                .requireIssuer(issuer)
                .requireAudience(audience)
                .build()
                .parseClaimsJws(token);

        return claimsJws.getBody();
    }
}
