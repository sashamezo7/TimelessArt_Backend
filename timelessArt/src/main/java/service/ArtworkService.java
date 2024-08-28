package service;


import DTO.ArtworkDTO;
import DTO.ArtworkListDTO;
import Mapper.ArtworkImageMapper;
import entity.ArtworkEntity;
import entity.ImageEntity;
import entity.ArtistEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import repo.ArtistRepo;
import repo.ArtworkRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class ArtworkService {

    @Inject
    ArtworkRepo artworkRepository;

    @Inject
    ArtistRepo artistRepository;


    @Transactional
    public List<ArtworkEntity> createArtworks(ArtworkListDTO artworkListDTO, int artistId) {

        ArtistEntity artist = artistRepository.findById((long) artistId);

        if (artist == null) {
            throw new IllegalArgumentException("Artist with ID " + artistId + " not found.");
        }


        return artworkListDTO.getArtworks().stream()
                .map(artworkDTO -> {
                    ArtworkEntity artwork = ArtworkImageMapper.toEntity(artworkDTO);
                    artwork.setArtist(artist);
                    return artwork;
                })
                .peek(artworkRepository::persist)
                .collect(Collectors.toList());
    }

    @Transactional
    public boolean deleteArtwork(Long artworkId) {
        ArtworkEntity artwork = artworkRepository.findById(artworkId);
        if (artwork == null) {
            return false;
        }
        artworkRepository.delete(artwork);
        return true;
    }

    public boolean isOwner(String email, Long artworkId){
        ArtworkEntity artwork = artworkRepository.findById(artworkId);
        return artwork.getArtist().getAccount().getEmail().equals(email);
    }

    @Transactional
    public List<ArtworkDTO> artworksByArtist(Long artistId) {
        List<ArtworkEntity> artworks = artworkRepository.findByArtistId(artistId);
        return artworks.stream()
                .map(ArtworkImageMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public Optional<ArtworkDTO> getArtworkById(Long artworkId) {
        ArtworkEntity artwork = artworkRepository.findById(artworkId);
        if (artwork == null) {
            return Optional.empty();
        }
        ArtworkDTO artworkDTO = ArtworkImageMapper.toDTO(artwork);
        return Optional.of(artworkDTO);
    }
    @Transactional
    public List<ArtworkDTO> getArtworks(ArtworkEntity.typeArtwork typeArtwork){
        return artworkRepository.findByTypeArtwork(typeArtwork).stream()
                .map(ArtworkImageMapper::toDTO)
                .collect(Collectors.toList());
    }
    @Transactional
    public List<ArtworkDTO> getAllArtworks(){
        return artworkRepository.findAll().stream()
                .map(ArtworkImageMapper::toDTO)
                .collect(Collectors.toList());
    }

}
