package controller;

import DTO.ArtworkDTO;
import DTO.ArtworkListDTO;
import entity.ArtworkEntity;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import response.MessageResponse;
import service.ArtworkService;

import java.util.List;
import java.util.Optional;

@Path("/artwork")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ArtworkController implements controller.Repo.ArtworkControllerRepo {
    @Inject
    ArtworkService artworkService;

    @Inject
    SecurityContext securityContext;



    @Override
    @POST
    @Path("/add")
    @RolesAllowed({"ADMIN", "ARTIST"})
    public Response createArtworks(ArtworkListDTO artworkListDTO, @QueryParam("artistId") int artistId) {
        if (artworkListDTO == null || artworkListDTO.getArtworks().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse("Invalid artwork list"))
                    .build();
        }
        try {
            List<ArtworkEntity> artworks = artworkService.createArtworks(artworkListDTO, artistId);
            return Response.status(Response.Status.CREATED)
                    .entity(new MessageResponse("Artwork was created"))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

    @Override
    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"ADMIN", "ARTIST"})
    public Response deleteArtwork(@PathParam("id") Long id) {
        String email = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("ADMIN")) {
           if(artworkService.deleteArtwork(id)) {
               Response.status(Response.Status.OK)
                       .entity(new MessageResponse("Artwork was deleted"))
                       .build();
           }else {
               Response.status(Response.Status.NOT_FOUND)
                       .entity(new MessageResponse("Artwork not found"))
                       .build();
           }
        }
        if (securityContext.isUserInRole("ARTIST")) {
            if (artworkService.isOwner(email, id)){
                if(artworkService.deleteArtwork(id)) {
                    Response.status(Response.Status.OK)
                            .entity(new MessageResponse("Artwork was deleted"))
                            .build();
                }else {
                    Response.status(Response.Status.NOT_FOUND)
                            .entity(new MessageResponse("Artwork not found"))
                            .build();
                }
            } else {
                return Response.status(Response.Status.FORBIDDEN).build();
            }
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Override
    @GET
    @Path("/all/by/artist")
    @PermitAll
    public Response getArtworksByArtist(@QueryParam("artistId") Long artistId){
        try{
            List<ArtworkDTO> artworks = artworkService.artworksByArtist(artistId);
            if(artworks.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new MessageResponse("Artist Id does not exist"))
                        .build();
            }else{
                return Response.status(Response.Status.OK)
                        .entity(artworks)
                        .build();
            }
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    @Override
    @GET
    @Path("/by/{id}")
    @PermitAll
    public Response getArtworkById(@PathParam("id") Long artworkId){
        try {
            Optional<ArtworkDTO> artworkDTO = artworkService.getArtworkById(artworkId);
            if(artworkDTO.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new MessageResponse("Artwork id was not found"))
                        .build();
            }
            else {
                return Response.status(Response.Status.OK)
                        .entity(artworkDTO)
                        .build();
            }
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    @Override
    @GET
    @Path("/by/type")
    @PermitAll
    public Response getArtworks(@QueryParam("typeArtwork") String typeArtwork){
        try{
            List<ArtworkDTO> artworks = artworkService.getArtworks(ArtworkEntity.typeArtwork.valueOf(typeArtwork));
            if(artworks.isEmpty()){
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(new MessageResponse("Artworks does not exists"))
                        .build();
            }else{
                return Response.status(Response.Status.OK)
                        .entity(artworks)
                        .build();
            }
        }catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }
    @Override
    @GET
    @Path("/all/artworks")
    @PermitAll
    public Response getAllArtworks(){
        try{
            List<ArtworkDTO> artworks = artworkService.getAllArtworks();
            if(artworks.isEmpty()){
              return Response.status(Response.Status.NOT_FOUND)
                        .entity(new MessageResponse("No artworks were found"))
                        .build();
            }
            else {
                return Response.status(Response.Status.OK)
                        .entity(artworks)
                        .build();
            }
        }catch (Exception e){
           return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new MessageResponse(e.getMessage()))
                    .build();
        }
    }

}


