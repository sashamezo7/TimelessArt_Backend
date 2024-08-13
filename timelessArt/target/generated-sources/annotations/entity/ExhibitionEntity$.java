package entity;

import com.speedment.jpastreamer.field.ComparableField;
import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;
import entity.ExhibitionEntity.exhibitionStatus;

import java.sql.Date;
import java.util.List;

/**
 * The generated base for entity {@link ExhibitionEntity} representing entities
 * of the {@code exhibitionEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class ExhibitionEntity$ {
    
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "artworks".
     */
    public static final ReferenceField<ExhibitionEntity, List<ArtworkEntity>> artworks = ReferenceField.create(
        ExhibitionEntity.class,
        "artworks",
        ExhibitionEntity::getArtworks,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "name".
     */
    public static final StringField<ExhibitionEntity> name = StringField.create(
        ExhibitionEntity.class,
        "name",
        ExhibitionEntity::getName,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "status".
     */
    public static final ComparableField<ExhibitionEntity, exhibitionStatus> status = ComparableField.create(
        ExhibitionEntity.class,
        "status",
        ExhibitionEntity::getStatus,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "endDate".
     */
    public static final ComparableField<ExhibitionEntity, Date> endDate = ComparableField.create(
        ExhibitionEntity.class,
        "endDate",
        ExhibitionEntity::getEndDate,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "startDate".
     */
    public static final ComparableField<ExhibitionEntity, Date> startDate = ComparableField.create(
        ExhibitionEntity.class,
        "startDate",
        ExhibitionEntity::getStartDate,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "artists".
     */
    public static final ReferenceField<ExhibitionEntity, List<ArtistEntity>> artists = ReferenceField.create(
        ExhibitionEntity.class,
        "artists",
        ExhibitionEntity::getArtists,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "id".
     */
    public static final IntField<ExhibitionEntity> id = IntField.create(
        ExhibitionEntity.class,
        "id",
        ExhibitionEntity::getId,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field "location".
     */
    public static final StringField<ExhibitionEntity> location = StringField.create(
        ExhibitionEntity.class,
        "location",
        ExhibitionEntity::getLocation,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field
     * "description".
     */
    public static final StringField<ExhibitionEntity> description = StringField.create(
        ExhibitionEntity.class,
        "description",
        ExhibitionEntity::getDescription,
        false
    );
    /**
     * This Field corresponds to the {@link ExhibitionEntity} field
     * "pictureUrl".
     */
    public static final StringField<ExhibitionEntity> pictureUrl = StringField.create(
        ExhibitionEntity.class,
        "pictureUrl",
        ExhibitionEntity::getPictureUrl,
        false
    );
}