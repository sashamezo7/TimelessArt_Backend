package Mapper;

import DTO.ArtworkDTO;
import entity.ArtworkEntity;
import entity.ImageEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ArtworkImageMapper {

    public static ArtworkDTO toDTO(ArtworkEntity entity) {
        if (entity == null) {
            return null;
        }

        return ArtworkDTO.builder()
                .id((long) entity.getId())
                .title(entity.getTitle())
                .artworkType(entity.getArtworkType().name())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .frame(entity.isFrame())
                .technique(entity.getTechnique())
                .length(entity.getLength())
                .width(entity.getWidth())
                .height(entity.getHeight())
                .imageUrls(entity.getImage() != null ? entity.getImage().stream()
                        .map(ImageEntity::getUrl)
                        .collect(Collectors.toList()) : null)
                .previewUrl(entity.getImage() != null ? entity.getImage().stream()
                        .map(ImageEntity::getPreviewUrl)
                        .collect(Collectors.toList()) : null)
                .build();
    }


    public static ArtworkEntity toEntity(ArtworkDTO dto) {
        if (dto == null) {
            return null;
        }

        ArtworkEntity entity = ArtworkEntity.builder()
                .title(dto.getTitle())
                .artworkType(ArtworkEntity.typeArtwork.valueOf(dto.getArtworkType()))
                .description(dto.getDescription())
                .price(dto.getPrice())
                .frame(dto.isFrame())
                .technique(dto.getTechnique())
                .length(dto.getLength())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .build();

        if (dto.getImageUrls() != null) {
            List<ImageEntity> images = dto.getImageUrls().stream()
                    .map(url -> ImageEntity.builder()
                            .url(url)
                            .artwork(entity)
                            .build())
                    .collect(Collectors.toList());


            entity.setImage(images);
        }


        return entity;
    }

}
