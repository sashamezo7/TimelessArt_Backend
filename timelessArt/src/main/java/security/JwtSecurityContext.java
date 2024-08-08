package security;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

public class JwtSecurityContext implements SecurityContext {

    private final Claims claims;
    private static final Logger LOGGER = Logger.getLogger(JwtSecurityContext.class.getName());

    public JwtSecurityContext(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Principal getUserPrincipal() {
        return claims::getSubject;
    }

    @Override
    public boolean isUserInRole(String role) {
        // Obține lista de roluri din claims
        List<String> roles = claims.get("roles", List.class);
        if (roles != null) {
            // Verifică dacă rolul specificat este prezent în lista de roluri
            boolean hasRole = roles.contains(role);
            LOGGER.info("User has role " + role + ": " + hasRole);
            return hasRole;
        } else {
            LOGGER.warning("No roles found in token");
            return false;
        }
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
