package security;

import io.jsonwebtoken.Claims;
import jakarta.annotation.Priority;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.core.SecurityContext;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Provider
@Priority(500)
public class JwtFilter implements ContainerRequestFilter {

    @Inject
    JwtService jwtService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        String path = requestContext.getUriInfo().getPath();
        String authHeader = requestContext.getHeaderString("Authorization");

        if (path.equals("/accounts/login") || path.equals("/accounts/create")) {
            return;
        }

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        // Extract token from header
        String token = authHeader.substring("Bearer ".length());
        Claims claims;
        try {
            claims = jwtService.verifyJwtToken(token);
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            return;
        }

        if(path.contains("client")){
           if(!jwtService.hasRole(token, "CLIENT") || !jwtService.hasRole(token, "ADMIN")){
               requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
           }
        }
        if(path.contains("admin")){
            if (!jwtService.hasRole(token,"ADMIN")){
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }
        if(path.contains("artist")){
            if (!jwtService.hasRole(token,"ARTIST") || !jwtService.hasRole(token,"ADMIN")){
                requestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
            }
        }

    }


}



