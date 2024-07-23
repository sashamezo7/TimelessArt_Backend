package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "artwork", schema = "timelessart", catalog = "")
public class ArtworkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artwork")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_artist")
    private ArtistEntity artist;

    @OneToMany(mappedBy = "artwork")
    private List<ReviewEntity> reviews;

    @ManyToOne
    @JoinColumn(name = "id_exhibition")
    private ExhibitionEntity exhibition;
    @Basic
    @Column(name = "title")
    private String title;

    @Basic
    @Column(name = "artwork_date")
    private Date artworkDate;
    @Basic
    @Column(name = "artwork_type")
    @Enumerated(EnumType.STRING)
    private typeArtwork artworkType;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "status")
    private artworkStatus status;
    @Basic
    @Column(name = "adding_date")
    private Timestamp addingDate;

    public enum typeArtwork {pictura,sculptura,grafica,fotografie,altul};
    public enum artworkStatus {disponibil,vandut, in_expozitie, indisponibil};

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArtistEntity getArtist() {
        return artist;
    }

    public void setArtist(ArtistEntity artist) {
        this.artist = artist;
    }

    public Date getArtworkDate() {
        return artworkDate;
    }

    public typeArtwork getArtworkType() {
        return artworkType;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public artworkStatus getStatus() {
        return status;
    }

    public Timestamp getAddingDate() {
        return addingDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setArtworkDate(Date artworkDate) {
        this.artworkDate = artworkDate;
    }

    public void setArtworkType(typeArtwork artworkType) {
        this.artworkType = artworkType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(artworkStatus status) {
        this.status = status;
    }

    public void setAddingDate(Timestamp addingDate) {
        this.addingDate = addingDate;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public List<ReviewEntity> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewEntity> reviews) {
        this.reviews = reviews;
    }

    public ExhibitionEntity getExhibition() {
        return exhibition;
    }

    public void setExhibition(ExhibitionEntity exhibition) {
        this.exhibition = exhibition;
    }
}
