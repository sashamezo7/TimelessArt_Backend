package entity;

import com.speedment.jpastreamer.field.BooleanField;
import com.speedment.jpastreamer.field.ComparableField;
import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;
import entity.ArtistEntity.artistType;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The generated base for entity {@link ArtistEntity} representing entities of
 * the {@code artistEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class ArtistEntity$ {
    
    /**
     * This Field corresponds to the {@link ArtistEntity} field "firstName".
     */
    public static final StringField<ArtistEntity> firstName = StringField.create(
        ArtistEntity.class,
        "firstName",
        ArtistEntity::getFirstName,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "name".
     */
    public static final StringField<ArtistEntity> name = StringField.create(
        ArtistEntity.class,
        "name",
        ArtistEntity::getName,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field
     * "profilePictureUrl".
     */
    public static final StringField<ArtistEntity> profilePictureUrl = StringField.create(
        ArtistEntity.class,
        "profilePictureUrl",
        ArtistEntity::getProfilePictureUrl,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "videos".
     */
    public static final ReferenceField<ArtistEntity, List<VideoEntity>> videos = ReferenceField.create(
        ArtistEntity.class,
        "videos",
        ArtistEntity::getVideos,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "status".
     */
    public static final BooleanField<ArtistEntity> status = BooleanField.create(
        ArtistEntity.class,
        "status",
        ArtistEntity::isStatus,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "account".
     */
    public static final ReferenceField<ArtistEntity, AccountEntity> account = ReferenceField.create(
        ArtistEntity.class,
        "account",
        ArtistEntity::getAccount,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "alias".
     */
    public static final StringField<ArtistEntity> alias = StringField.create(
        ArtistEntity.class,
        "alias",
        ArtistEntity::getAlias,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "phone".
     */
    public static final StringField<ArtistEntity> phone = StringField.create(
        ArtistEntity.class,
        "phone",
        ArtistEntity::getPhone,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "typeArtist".
     */
    public static final ComparableField<ArtistEntity, artistType> typeArtist = ComparableField.create(
        ArtistEntity.class,
        "typeArtist",
        ArtistEntity::getTypeArtist,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "exhibitions".
     */
    public static final ReferenceField<ArtistEntity, List<ExhibitionEntity>> exhibitions = ReferenceField.create(
        ArtistEntity.class,
        "exhibitions",
        ArtistEntity::getExhibitions,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "artworks".
     */
    public static final ReferenceField<ArtistEntity, List<ArtworkEntity>> artworks = ReferenceField.create(
        ArtistEntity.class,
        "artworks",
        ArtistEntity::getArtworks,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "birthDate".
     */
    public static final ComparableField<ArtistEntity, Date> birthDate = ComparableField.create(
        ArtistEntity.class,
        "birthDate",
        ArtistEntity::getBirthDate,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "biography".
     */
    public static final StringField<ArtistEntity> biography = StringField.create(
        ArtistEntity.class,
        "biography",
        ArtistEntity::getBiography,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field
     * "registrationDate".
     */
    public static final ComparableField<ArtistEntity, Timestamp> registrationDate = ComparableField.create(
        ArtistEntity.class,
        "registrationDate",
        ArtistEntity::getRegistrationDate,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "id".
     */
    public static final IntField<ArtistEntity> id = IntField.create(
        ArtistEntity.class,
        "id",
        ArtistEntity::getId,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "city".
     */
    public static final StringField<ArtistEntity> city = StringField.create(
        ArtistEntity.class,
        "city",
        ArtistEntity::getCity,
        false
    );
    /**
     * This Field corresponds to the {@link ArtistEntity} field "website".
     */
    public static final StringField<ArtistEntity> website = StringField.create(
        ArtistEntity.class,
        "website",
        ArtistEntity::getWebsite,
        false
    );
}