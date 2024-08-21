package DTO.mapper;

import DTO.ArtworkDTO;
import entity.ArtworkEntity;
import entity.ImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ArtworkMapper {
    ArtworkMapper INSTANCE = Mappers.getMapper(ArtworkMapper.class);

    //@Mapping(source = "image", target = "imageUrls", qualifiedByName = "imageEntitiesToImageUrls")
    ArtworkDTO toDTO(ArtworkEntity entity);

    ArtworkEntity toEntity(ArtworkDTO dto);

    @Named("imageEntitiesToImageUrls")
    default List<String> imageEntitiesToImageUrls(List<ImageEntity> images) {
        return images.stream()
                .map(ImageEntity::getUrl)
                .collect(Collectors.toList());
    }

}
