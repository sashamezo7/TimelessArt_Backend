package controller.Repo;

import DTO.ClientDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public interface ClientsControllerRepo {
    @GET
    @Path("/me")
    @RolesAllowed({"CLIENT", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    Response getMyInfo();

    @GET
    @Path("All")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    Response getAllClients();

    @POST
    @Path("Register")
    @RolesAllowed({"CLIENT", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    Response completeProfile(ClientDTO clientDTO);

    @PATCH
    @Path("UpdateClient")
    @RolesAllowed("CLIENT")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response updateProfile(@QueryParam("fieldName") String fieldName, @QueryParam("newValue") String newValue);
}
