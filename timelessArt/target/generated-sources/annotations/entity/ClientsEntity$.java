package entity;

import com.speedment.jpastreamer.field.ComparableField;
import com.speedment.jpastreamer.field.IntField;
import com.speedment.jpastreamer.field.ReferenceField;
import com.speedment.jpastreamer.field.StringField;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * The generated base for entity {@link ClientsEntity} representing entities of
 * the {@code clientsEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class ClientsEntity$ {
    
    /**
     * This Field corresponds to the {@link ClientsEntity} field "reviews".
     */
    public static final ReferenceField<ClientsEntity, List<ReviewEntity>> reviews = ReferenceField.create(
        ClientsEntity.class,
        "reviews",
        ClientsEntity::getReviews,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "phone".
     */
    public static final StringField<ClientsEntity> phone = StringField.create(
        ClientsEntity.class,
        "phone",
        ClientsEntity::getPhone,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "birthDate".
     */
    public static final ComparableField<ClientsEntity, Date> birthDate = ComparableField.create(
        ClientsEntity.class,
        "birthDate",
        ClientsEntity::getBirthDate,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "orders".
     */
    public static final ReferenceField<ClientsEntity, List<OrderEntity>> orders = ReferenceField.create(
        ClientsEntity.class,
        "orders",
        ClientsEntity::getOrders,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field
     * "registrationDate".
     */
    public static final ComparableField<ClientsEntity, Timestamp> registrationDate = ComparableField.create(
        ClientsEntity.class,
        "registrationDate",
        ClientsEntity::getRegistrationDate,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "id".
     */
    public static final IntField<ClientsEntity> id = IntField.create(
        ClientsEntity.class,
        "id",
        ClientsEntity::getId,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "country".
     */
    public static final StringField<ClientsEntity> country = StringField.create(
        ClientsEntity.class,
        "country",
        ClientsEntity::getCountry,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "account".
     */
    public static final ReferenceField<ClientsEntity, AccountEntity> account = ReferenceField.create(
        ClientsEntity.class,
        "account",
        ClientsEntity::getAccount,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "name".
     */
    public static final StringField<ClientsEntity> name = StringField.create(
        ClientsEntity.class,
        "name",
        ClientsEntity::getName,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "address".
     */
    public static final StringField<ClientsEntity> address = StringField.create(
        ClientsEntity.class,
        "address",
        ClientsEntity::getAddress,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "city".
     */
    public static final StringField<ClientsEntity> city = StringField.create(
        ClientsEntity.class,
        "city",
        ClientsEntity::getCity,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "firstName".
     */
    public static final StringField<ClientsEntity> firstName = StringField.create(
        ClientsEntity.class,
        "firstName",
        ClientsEntity::getFirstName,
        false
    );
    /**
     * This Field corresponds to the {@link ClientsEntity} field "postalCode".
     */
    public static final StringField<ClientsEntity> postalCode = StringField.create(
        ClientsEntity.class,
        "postalCode",
        ClientsEntity::getPostalCode,
        false
    );
}