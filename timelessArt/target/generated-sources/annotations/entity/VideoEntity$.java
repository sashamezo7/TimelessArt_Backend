package entity;

import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;

/**
 * The generated base for entity {@link VideoEntity} representing entities of
 * the {@code videoEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class VideoEntity$ {
    
    /**
     * This Field corresponds to the {@link VideoEntity} field "artist".
     */
    public static final ReferenceField<VideoEntity, ArtistEntity> artist = ReferenceField.create(
        VideoEntity.class,
        "artist",
        VideoEntity::getArtist,
        false
    );
    /**
     * This Field corresponds to the {@link VideoEntity} field "artwork".
     */
    public static final ReferenceField<VideoEntity, ArtworkEntity> artwork = ReferenceField.create(
        VideoEntity.class,
        "artwork",
        VideoEntity::getArtwork,
        false
    );
    /**
     * This Field corresponds to the {@link VideoEntity} field "url".
     */
    public static final StringField<VideoEntity> url = StringField.create(
        VideoEntity.class,
        "url",
        VideoEntity::getUrl,
        false
    );
    /**
     * This Field corresponds to the {@link VideoEntity} field "id".
     */
    public static final IntField<VideoEntity> id = IntField.create(
        VideoEntity.class,
        "id",
        VideoEntity::getId,
        false
    );
}