package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artist", schema = "timelessart")
public class ArtistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artist")
    private int id;

    @OneToOne
    @JoinColumn(name = "account", nullable = false)
    private AccountEntity account;

    @OneToMany(mappedBy = "artist")
    private List <ArtworkEntity> artworks;

    @ManyToMany
    @JoinTable(
            name = "artist_exhibition",
            joinColumns = @JoinColumn(name = "id_artist"),
            inverseJoinColumns = @JoinColumn(name = "id_exhibition")
    )
    private List <ExhibitionEntity> exhibitions;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "artist")
    private List<VideoEntity> videos;

    @Column(name = "first_mane", nullable = false)
    private String firstName;
    @Column(name = "alias")
    private String alias;

    @Column(name = "phone")
    private String phone;
    @Column(name = "city")
    private String city;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "biography")
    private String biography;
    @Column(name = "website")
    private String website;

    @Column(name = "registration_date", updatable = false)
    private Timestamp registrationDate;
    @Column(name = "status")
    private boolean status;

    @Column(name = "artist_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private artistType typeArtist;

    @Column(name = "profile_picture_url")
    private String profilePictureUrl;


    public enum artistType {pictor,sculptor,grafician,altul};


}
