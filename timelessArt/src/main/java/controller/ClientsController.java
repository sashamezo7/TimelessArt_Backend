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
public class ClientsController {
    @Inject
    ClientsService clientService;

    @Inject
    SecurityContext securityContext;

    @GET
    @Path("/me")
    @RolesAllowed("CLIENT")
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

    @POST
    @Path("Register")
    @RolesAllowed("CLIENT")
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
}
