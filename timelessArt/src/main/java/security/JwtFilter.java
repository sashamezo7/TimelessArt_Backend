package security;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.logging.Logger;

@Provider
@Priority(Priorities.AUTHORIZATION)
public class JwtFilter implements ContainerRequestFilter {

    @Inject
    JwtService jwtService;

    private static final Logger LOGGER = Logger.getLogger(JwtFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();

        if (path.equals("/accounts/login") || path.equals("/accounts/create") || path.equals("/accounts/request-password-reset")||path.equals("/accounts/reset-password")||path.equals("/accounts/send")) {
            return;
        }

        String authorizationHeader = requestContext.getHeaderString("Authorization");

        LOGGER.info("Authorization Header: " + authorizationHeader);

        // Check if the Authorization header is missing or invalid
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            LOGGER.warning("Missing or invalid Authorization header");
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        String token = authorizationHeader.substring("Bearer ".length()).trim();

        try {
            // Verify the JWT token
            Claims claims = jwtService.verifyJwtToken(token);
            JwtSecurityContext securityContext = new JwtSecurityContext(claims);
            requestContext.setSecurityContext(securityContext);
        } catch (Exception e) {
            // Handle token verification failure
            LOGGER.severe("Token verification failed: " + e.getMessage());
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}