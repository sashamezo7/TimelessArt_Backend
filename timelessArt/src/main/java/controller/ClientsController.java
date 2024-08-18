package controller;

import DTO.ClientDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.ClientsService;
import security.JwtService;

import java.util.List;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientsController {
    @Inject
    ClientsService clientService;

    @Inject
    JwtService jwtService;

    @GET
    @Path("/me")
    @RolesAllowed("CLIENT")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMyInfo(@Context HttpHeaders httpHeaders) {
        try {
            String userEmail = jwtService.extractEmailFromHeaders(httpHeaders);
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
    public Response completeProfile(ClientDTO clientDTO, @Context HttpHeaders httpHeaders) {
        try {
            String userEmail = jwtService.extractEmailFromHeaders(httpHeaders);
            clientService.createNewClient(clientDTO, userEmail);
            return Response.status(Response.Status.OK).entity("Profile completed successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Error completing profile: " + e.getMessage()).build();
        }
    }
}