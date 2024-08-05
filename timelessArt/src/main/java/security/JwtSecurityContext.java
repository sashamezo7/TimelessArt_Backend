package security;

import io.jsonwebtoken.Claims;

import jakarta.ws.rs.core.SecurityContext;
import java.security.Principal;
import java.util.List;

public class JwtSecurityContext implements SecurityContext {

    private final Claims claims;

    public JwtSecurityContext(Claims claims) {
        this.claims = claims;
    }

    @Override
    public Principal getUserPrincipal() {
        return claims::getSubject;
    }

    @Override
    public boolean isUserInRole(String role) {
        List<String> roles = claims.get("roles", List.class);
       if(roles.contains(role)){
           System.out.println(true);
           return true;
       }
       else {
           System.out.println(false);
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
