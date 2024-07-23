package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "artist", schema = "timelessart", catalog = "")
public class ArtistEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artist")
    private int id;

    @OneToMany(mappedBy = "artist")
    private List <ArtworkEntity> artworks;
    @ManyToMany
    @JoinTable(
            name = "artist_exhibition",
            joinColumns = @JoinColumn(name = "id_artist"),
            inverseJoinColumns = @JoinColumn(name = "id_exhibition")
    )
    private List <ExhibitionEntity> exhibitions;
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    @Basic
    @Column(name = "first_mane", nullable = false)
    private String firstName;
    @Basic
    @Column(name = "alias")
    private String alias;
    @Basic
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "birth_date")
    private Date birthDate;
    @Basic
    @Column(name = "biography")
    private String biography;
    @Basic
    @Column(name = "website")
    private String website;

    @Basic
    @Column(name = "registration_date", updatable = false)
    private Timestamp registrationDate;
    @Basic
    @Column(name = "status")
    private boolean status;

    @Basic
    @Column(name = "artist_type")
    @Enumerated(EnumType.STRING)
    private artistType typeArtist;

    public enum artistType {pictor,sculptor,grafician,altul};

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAlias() {
        return alias;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getBiography() {
        return biography;
    }

    public String getWebsite() {
        return website;
    }

    public Timestamp getRegistrationDate() {
        return registrationDate;
    }

    public boolean isStatus() {
        return status;
    }


    public artistType getTypeArtist() {
        return typeArtist;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setRegistrationDate(Timestamp registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }


    public void setTypeArtist(artistType typeArtist) {
        this.typeArtist = typeArtist;
    }

    public List<ArtworkEntity> getArtworks() {
        return artworks;
    }

    public List<ExhibitionEntity> getExhibitions() {
        return exhibitions;
    }

    public void setArtworks(List<ArtworkEntity> artworks) {
        this.artworks = artworks;
    }

    public void setExhibitions(List<ExhibitionEntity> exhibitions) {
        this.exhibitions = exhibitions;
    }

}
