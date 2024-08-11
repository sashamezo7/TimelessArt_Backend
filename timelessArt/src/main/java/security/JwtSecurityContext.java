package security;
import io.jsonwebtoken.Claims;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
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
        String userRoles = (String) claims.get("roles");

        // Logare pentru verificarea rolurilor
        LOGGER.info("Checking role: " + role);
        LOGGER.info("User roles from JWT: " + userRoles);

        boolean result = userRoles != null && userRoles.equals(role);

        // Logare pentru a vedea rezultatul
        LOGGER.info("Is user in role '" + role + "': " + result);

        return result;
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
