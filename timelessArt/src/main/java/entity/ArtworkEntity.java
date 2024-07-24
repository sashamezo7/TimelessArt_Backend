package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "artwork", schema = "timelessart")
public class ArtworkEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_artwork")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "id_artist", nullable = false)
    private ArtistEntity artist;

    @OneToMany(mappedBy = "artwork")
    private List<ReviewEntity> reviews;

    @ManyToOne
    @JoinColumn(name = "id_exhibition")
    private ExhibitionEntity exhibition;

    @OneToMany(mappedBy = "artwork")
    private List <ImageEntity> image;

    @OneToMany(mappedBy = "artwork")
    private List <VideoEntity> video;

    @Column(name = "title")
    private String title;

    @Column(name = "artwork_date")
    private Date artworkDate;
    @Column(name = "artwork_type")
    @Enumerated(EnumType.STRING)
    private typeArtwork artworkType;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "status")
    private artworkStatus status;
    @Column(name = "adding_date")
    private Timestamp addingDate;


    public enum typeArtwork {pictura,sculptura,grafica,fotografie,altul};
    public enum artworkStatus {disponibil,vandut,in_expozitie,indisponibil};

}
