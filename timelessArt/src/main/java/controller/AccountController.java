package controller;

import DTO.AccountRequest;
import DTO.AuthenticationRequest;
import DTO.AuthenticationResponse;
import entity.AccountEntity;
import exception.InvalidCredentialsException;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import repo.AccountRepo;
import security.JwtFilter;
import service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Tag(name = "Account Controller", description = "Operations related to accounts")
@SecurityScheme(securitySchemeName = "Authorization",
        description = "JWT Bearer Token",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer",
        bearerFormat = "JWT")
@Path("/api/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {

    @Inject
    AccountService accountService;

    @Inject
    JwtFilter jwtFilter;
    @POST
    @Path("/create")
    public Response createAccount(AccountRequest request) {
        try {
            AccountEntity account = accountService.createAccount(request.getEmail(), request.getPassword());
            return Response.status(Response.Status.CREATED).entity(account).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAccount(@PathParam("id") Long id) {
        boolean isDeleted = accountService.deleteAccount(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Account not found").build();
        }
    }
    @POST
    @Path("/login")
    public Response login(AuthenticationRequest request) {
        try {
            String token = accountService.authenticate(request.getEmail(), request.getPassword());
            return Response.ok(new AuthenticationResponse(token)).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed("ADMIN")
    public Response getAllAccounts() {
            List<AccountEntity> accounts = accountService.getAllAccounts();
            return Response.status(Response.Status.FOUND).entity(accounts).build();
    }

}
