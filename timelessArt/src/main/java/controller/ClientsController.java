package controller;

import DTO.ClientDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import service.ClientsService;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientsController implements controller.Repo.ClientsControllerRepo {
    @Inject
    ClientsService clientService;

    @Inject
    SecurityContext securityContext;

    @Override
    @GET
    @Path("/me")
    @RolesAllowed({"CLIENT", "ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyInfo() {
        try {
            String userEmail = securityContext.getUserPrincipal().getName();
            ClientDTO clientDTO = clientService.getInfoAboutMe(userEmail);
            return Response.ok(clientDTO).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @Override
    @GET
    @Path("All")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClients() {
        try {
            List<ClientDTO> clientsDTO = clientService.getAllClients();
            return Response.ok(clientsDTO).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @Override
    @POST
    @Path("Register")
    @RolesAllowed({"CLIENT","ADMIN"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response completeProfile(ClientDTO clientDTO) {
        try {
            String userEmail = securityContext.getUserPrincipal().getName();
            clientService.createNewClient(clientDTO, userEmail);
            return Response.status(Response.Status.OK).entity("Profile completed successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error completing profile: " + e.getMessage()).build();
        }
    }
    @Override
    @PATCH
    @Path("UpdateClient")
    @RolesAllowed("CLIENT")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProfile(@QueryParam("fieldName") String fieldName, @QueryParam("newValue") String newValue) {
        try {
            String email = securityContext.getUserPrincipal().getName();
            clientService.updateClientField(email, fieldName, newValue);
            return Response.status(Response.Status.OK).entity("Profile updated successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error updating profile: " + e.getMessage()).build();
        }
    }
}
