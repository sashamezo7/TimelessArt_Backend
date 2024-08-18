package controller;

import DTO.ArtworkDTO;
import DTO.ArtworkListDTO;
import entity.ArtworkEntity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import service.ArtworkService;

import java.util.List;

@Path("/artwork")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtworkController {
    @Inject
    ArtworkService artworkService;

    @Inject
    SecurityContext securityContext;


    @POST
    @Path("/add")
    @RolesAllowed({"ADMIN", "ARTIST"})
    public Response createArtworks(ArtworkListDTO artworkListDTO, @QueryParam("artistId") int artistId) {
        if (artworkListDTO == null || artworkListDTO.getArtworks().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid artwork list").build();
        }
        try {
            List<ArtworkEntity> artworks = artworkService.createArtworks(artworkListDTO, artistId);
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("delete/{id}")
    @RolesAllowed({"ADMIN", "ARTIST"})
    public Response deleteArtwork(@PathParam("id") Long id) {
        String email = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("ADMIN")) {
           if(artworkService.deleteArtwork(id)) {
               return Response.status(Response.Status.OK).entity("Deleted").build();
           }else {
               return Response.status(Response.Status.NOT_FOUND).entity("Artwork not found").build();
           }
        }

        if (securityContext.isUserInRole("ARTIST")) {
            if (artworkService.isOwner(email, id)){
                if(artworkService.deleteArtwork(id)) {
                    return Response.status(Response.Status.OK).entity("Deleted").build();
                }else {
                    return Response.status(Response.Status.NOT_FOUND).entity("Artwork not found").build();
                }
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}


