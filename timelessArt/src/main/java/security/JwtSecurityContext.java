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
        String userRole = (String) claims.get("roles");
        return role.equals(userRole);
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
