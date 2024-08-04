package security;

import io.jsonwebtoken.Claims;

import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

public class JwtSecurityContext implements SecurityContext {

    private final Claims claims;

    public JwtSecurityContext(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Principal getUserPrincipal() {
        return () -> claims.getSubject();
    }

    @Override
    public boolean isUserInRole(String role) {
        // Preia lista de roluri ca List<String> pentru a evita avertismentul de tip necontrolat
        List<String> roles = claims.get("roles", List.class);
        if (roles == null) {
            return false;
        }
        // Convertim lista generică la o listă de String
        List<String> rolesAsString = roles.stream()
                .map(Object::toString)
                .toList();
        // Verifică dacă lista de roluri conține rolul specificat
        return rolesAsString.contains(role);
    }

    @Override
    public boolean isSecure() {
        return "https".equals(System.getProperty("https.protocol"));
    }

    @Override
    public String getAuthenticationScheme() {
        return "Bearer";
    }
}
