package controller;

import DTO.*;
import entity.AccountEntity;
import exception.InvalidCredentialsException;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.jboss.resteasy.reactive.server.core.multipart.FormData;
import service.AccountService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.SimpleEmailTest;


import java.util.List;
import java.util.Map;

@Path("/accounts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {

    @Inject
    AccountService accountService;
    @Inject
    SimpleEmailTest emailTestService;


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
    @RolesAllowed("ADMIN")
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAccounts() {
        List<AccountEntity> accounts = accountService.getAllAccounts();
            return Response.status(Response.Status.OK).entity(accounts).build();

    }


    @POST
    @Path("/request-password-reset")
    @Operation(summary = "Request password reset", description = "Sends a password reset link to the specified email address.")
    @APIResponse(responseCode = "204", description = "Password reset email sent.")
    @APIResponse(responseCode = "400", description = "Invalid email address.")
    public Response requestPasswordReset(EmailRequest request) {
        String email = request.getEmail();
        try {
            accountService.requestPasswordReset(email);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"error\": \"" + e.getMessage() + "\"}")
                    .build();
        }
    }

    @POST
    @Path("/reset-password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response resetPassword(PasswordResetRequest request) {
        try {
            accountService.resetPassword(request.getToken(), request.getNewPassword());
            return Response.ok().entity("Password has been reset successfully").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @GET
    @Path("/send")
    @Blocking
    public Response sendTestEmail() {
        try {
            emailTestService.sendTestEmail();
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Failed to send test email: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/test/mail")
    public Uni<Response> sendEmailAsync() {
        return Uni.createFrom().item(() -> {
                    emailTestService.sendTestEmail();
                    return Response.status(Response.Status.NO_CONTENT).build();
                })
                .runSubscriptionOn(Infrastructure.getDefaultExecutor())
                .onFailure().recoverWithItem(e -> Response.status(Response.Status.BAD_REQUEST)
                        .entity("Failed to send email: " + e.getMessage())
                        .build());
    }

}



