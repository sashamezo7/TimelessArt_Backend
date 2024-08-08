package entity;

import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;

/**
 * The generated base for entity {@link ImageEntity} representing entities of
 * the {@code imageEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class ImageEntity$ {
    
    /**
     * This Field corresponds to the {@link ImageEntity} field "id".
     */
    public static final IntField<ImageEntity> id = IntField.create(
        ImageEntity.class,
        "id",
        ImageEntity::getId,
        false
    );
    /**
     * This Field corresponds to the {@link ImageEntity} field "artwork".
     */
    public static final ReferenceField<ImageEntity, ArtworkEntity> artwork = ReferenceField.create(
        ImageEntity.class,
        "artwork",
        ImageEntity::getArtwork,
        false
    );
    /**
     * This Field corresponds to the {@link ImageEntity} field "url".
     */
    public static final StringField<ImageEntity> url = StringField.create(
        ImageEntity.class,
        "url",
        ImageEntity::getUrl,
        false
    );
}