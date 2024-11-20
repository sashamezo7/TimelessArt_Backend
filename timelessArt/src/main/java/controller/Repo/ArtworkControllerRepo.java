package controller.Repo;

import DTO.ArtworkListDTO;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

public interface ArtworkControllerRepo {
    @POST
    @Path("/add")
    @RolesAllowed({"ADMIN", "ARTIST"})
    Response createArtworks(ArtworkListDTO artworkListDTO, @QueryParam("artistId") int artistId);

    @DELETE
    @Path("/delete/{id}")
    @RolesAllowed({"ADMIN", "ARTIST"})
    Response deleteArtwork(@PathParam("id") Long id);

    @GET
    @Path("/all/by/artist")
    @PermitAll
    Response getArtworksByArtist(@QueryParam("artistId") Long artistId);

    @GET
    @Path("/by/{id}")
    @PermitAll
    Response getArtworkById(@PathParam("id") Long artworkId);

    @GET
    @Path("/by/type")
    @PermitAll
    Response getArtworks(@QueryParam("typeArtwork") String typeArtwork);

    @GET
    @Path("/all/artworks")
    @PermitAll
    Response getAllArtworks();
}
