package entity;

import com.speedment.jpastreamer.field.BooleanField;
import com.speedment.jpastreamer.field.ComparableField;
import com.speedment.jpastreamer.field.StringField;
import entity.AccountEntity.Role;

/**
 * The generated base for entity {@link AccountEntity} representing entities of
 * the {@code accountEntity}-table in the database.
 * <p> This file has been automatically generated by JPAStreamer.
 * 
 * @author JPAStreamer
 */
public final class AccountEntity$ {
    
    /**
     * This Field corresponds to the {@link AccountEntity} field "password".
     */
    public static final StringField<AccountEntity> password = StringField.create(
        AccountEntity.class,
        "password",
        AccountEntity::getPassword,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "validAccount".
     */
    public static final BooleanField<AccountEntity> validAccount = BooleanField.create(
        AccountEntity.class,
        "validAccount",
        AccountEntity::isValidAccount,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "id".
     */
    public static final ComparableField<AccountEntity, Long> id = ComparableField.create(
        AccountEntity.class,
        "id",
        AccountEntity::getId,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "email".
     */
    public static final StringField<AccountEntity> email = StringField.create(
        AccountEntity.class,
        "email",
        AccountEntity::getEmail,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "keyArtist".
     */
    public static final StringField<AccountEntity> keyArtist = StringField.create(
        AccountEntity.class,
        "keyArtist",
        AccountEntity::getKeyArtist,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "token".
     */
    public static final StringField<AccountEntity> token = StringField.create(
        AccountEntity.class,
        "token",
        AccountEntity::getToken,
        false
    );
    /**
     * This Field corresponds to the {@link AccountEntity} field "role".
     */
    public static final ComparableField<AccountEntity, Role> role = ComparableField.create(
        AccountEntity.class,
        "role",
        AccountEntity::getRole,
        false
    );
}