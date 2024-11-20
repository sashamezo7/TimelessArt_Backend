package controller.Repo;

import DTO.AccountRequest;
import DTO.AuthenticationRequest;
import DTO.EmailRequest;
import DTO.PasswordResetRequest;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

public interface AccountControllerRepo  {
    @POST
    @Path("/create")
    Response createAccount(AccountRequest request);

    @DELETE
    @Path("/{id}")
    Response deleteAccount(@PathParam("id") Long id);

    @POST
    @Path("/login")
    Response login(AuthenticationRequest request);

    @GET
    @RolesAllowed("ADMIN")
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllAccounts();

    @POST
    @Path("/request-password-reset")
    @Operation(summary = "Request password reset", description = "Sends a password reset link to the specified email address.")
    @APIResponse(responseCode = "204", description = "Password reset email sent.")
    @APIResponse(responseCode = "400", description = "Invalid email address.")
    Response requestPasswordReset(EmailRequest request);

    @POST
    @Path("/reset-password")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response resetPassword(PasswordResetRequest request);

    @GET
    @Path("/send")
    @Blocking
    Response sendTestEmail();

    @POST
    @Path("/test/mail")
    Uni<Response> sendEmailAsync();
}
