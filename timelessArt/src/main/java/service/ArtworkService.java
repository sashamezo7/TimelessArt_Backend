package service;


import DTO.ArtworkDTO;
import DTO.ArtworkListDTO;
import DTO.mapper.ArtworkImageMapper;
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
        ArtistEntity artist = artistRepository.findById((long)artistId);
        List<ArtworkEntity> artworks = new ArrayList<>();


        for (ArtworkDTO artworkDTO : artworkListDTO.getArtworks()) {

            ArtworkEntity artwork = new ArtworkEntity();
            artwork.setTitle(artworkDTO.getTitle());
            artwork.setArtworkDate(null);
            artwork.setArtworkType(ArtworkEntity.typeArtwork.valueOf(artworkDTO.getArtworkType()));
            artwork.setDescription(artworkDTO.getDescription());
            artwork.setPrice(artworkDTO.getPrice());
            artwork.setStatus(ArtworkEntity.artworkStatus.disponibil);
            artwork.setFrame(artworkDTO.isFrame());
            artwork.setTechnique(artworkDTO.getTechnique());
            artwork.setHeight(artworkDTO.getHeight());
            artwork.setLength(artworkDTO.getLength());
            artwork.setWidth(artworkDTO.getWidth());
            artwork.setArtist(artist);

            List<ImageEntity> images = new ArrayList<>();
            for (String url : artworkDTO.getImageUrls()) {
                ImageEntity image = new ImageEntity();
                image.setUrl(url);
                image.setArtwork(artwork);
                images.add(image);
            }

            artwork.setImage(images);
            artworkRepository.persist(artwork);
            artworks.add(artwork);
        }

        return artworks;
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

}
