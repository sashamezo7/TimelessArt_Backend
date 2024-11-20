package service.Repo;

import DTO.ArtworkDTO;
import DTO.ArtworkListDTO;
import entity.ArtworkEntity;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

public interface ArtworkServiceRepo {
    @Transactional
    List<ArtworkEntity> createArtworks(ArtworkListDTO artworkListDTO, int artistId);

    @Transactional
    boolean deleteArtwork(Long artworkId);

    boolean isOwner(String email, Long artworkId);

    @Transactional
    List<ArtworkDTO> artworksByArtist(Long artistId);

    @Transactional
    Optional<ArtworkDTO> getArtworkById(Long artworkId);

    @Transactional
    List<ArtworkDTO> getArtworks(ArtworkEntity.typeArtwork typeArtwork);

    @Transactional
    List<ArtworkDTO> getAllArtworks();
}
