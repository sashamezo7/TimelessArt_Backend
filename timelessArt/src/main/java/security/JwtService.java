package security;

import entity.AccountEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;

@ApplicationScoped
public class JwtService {

    private final String issuer;
    private final String audience;
    private final long expirationTime;
    private final String privateKeyLocation;
    private final String publicKeyLocation;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public JwtService(@ConfigProperty(name = "mp.jwt.verify.issuer") String issuer,
                      @ConfigProperty(name = "mp.jwt.verify.audience") String audience,
                      @ConfigProperty(name = "jwt.expiration-time") long expirationTime,
                      @ConfigProperty(name = "mp.jwt.sign.key-location") String privateKeyLocation,
                      @ConfigProperty(name = "mp.jwt.verify.publickey.location") String publicKeyLocation) {
        this.issuer = issuer;
        this.audience = audience;
        this.expirationTime = expirationTime;
        this.privateKeyLocation = privateKeyLocation;
        this.publicKeyLocation = publicKeyLocation;
    }

    @PostConstruct
    public void init() throws Exception {
        byte[] privateKeyBytes = Files.readAllBytes(Paths.get(privateKeyLocation));
        byte[] publicKeyBytes = Files.readAllBytes(Paths.get(publicKeyLocation));

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");


        String privateKeyPEM = new String(privateKeyBytes)
                .replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decodedPrivateKey = Base64.getDecoder().decode(privateKeyPEM);
        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(decodedPrivateKey);
        privateKey = keyFactory.generatePrivate(privateKeySpec);

        String publicKeyPEM = new String(publicKeyBytes)
                .replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replaceAll("\\s", "");
        byte[] decodedPublicKey = Base64.getDecoder().decode(publicKeyPEM);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(decodedPublicKey);
        publicKey = keyFactory.generatePublic(publicKeySpec);
    }


    public String generateJwtToken(AccountEntity account) {
        Claims claims = Jwts.claims();
        claims.setSubject(account.getEmail());
        claims.put("roles", account.getRole().toString());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(issuer)
                .setAudience(audience)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime * 1000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    public Claims verifyJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .requireIssuer(issuer)
                .requireAudience(audience)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
