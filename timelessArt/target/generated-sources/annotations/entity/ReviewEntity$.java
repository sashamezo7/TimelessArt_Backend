package entity;

import com.speedment.jpastreamer.field.ComparableField;
import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;

import java.sql.Timestamp;

/**
 * The generated base for entity {@link ReviewEntity} representing entities of
 * the {@code reviewEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class ReviewEntity$ {
    
    /**
     * This Field corresponds to the {@link ReviewEntity} field "reviewDate".
     */
    public static final ComparableField<ReviewEntity, Timestamp> reviewDate = ComparableField.create(
        ReviewEntity.class,
        "reviewDate",
        ReviewEntity::getReviewDate,
        false
    );
    /**
     * This Field corresponds to the {@link ReviewEntity} field "rating".
     */
    public static final ComparableField<ReviewEntity, Integer> rating = ComparableField.create(
        ReviewEntity.class,
        "rating",
        ReviewEntity::getRating,
        false
    );
    /**
     * This Field corresponds to the {@link ReviewEntity} field "artwork".
     */
    public static final ReferenceField<ReviewEntity, ArtworkEntity> artwork = ReferenceField.create(
        ReviewEntity.class,
        "artwork",
        ReviewEntity::getArtwork,
        false
    );
    /**
     * This Field corresponds to the {@link ReviewEntity} field "reviewText".
     */
    public static final StringField<ReviewEntity> reviewText = StringField.create(
        ReviewEntity.class,
        "reviewText",
        ReviewEntity::getReviewText,
        false
    );
    /**
     * This Field corresponds to the {@link ReviewEntity} field "client".
     */
    public static final ReferenceField<ReviewEntity, ClientsEntity> client = ReferenceField.create(
        ReviewEntity.class,
        "client",
        ReviewEntity::getClient,
        false
    );
    /**
     * This Field corresponds to the {@link ReviewEntity} field "id".
     */
    public static final IntField<ReviewEntity> id = IntField.create(
        ReviewEntity.class,
        "id",
        ReviewEntity::getId,
        false
    );
}