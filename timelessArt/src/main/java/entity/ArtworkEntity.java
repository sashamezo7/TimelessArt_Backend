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

    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReviewEntity> reviews;

    @ManyToOne
    @JoinColumn(name = "id_exhibition")
    private ExhibitionEntity exhibition;

    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL, orphanRemoval = true)
    private List <ImageEntity> image;

    @OneToMany(mappedBy = "artwork", cascade = CascadeType.ALL, orphanRemoval = true)
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
    @Enumerated(EnumType.STRING)
    private artworkStatus status;
    @Column(name = "adding_date")
    private Timestamp addingDate;
    @Column(name = "frame")
    private boolean frame;
    @Column(name = "technique")
    private String technique;
    @Column(name = "length")
    private Float length;
    @Column(name = "width")
    private Float width;
    @Column(name = "height")
    private Float height;

    public enum typeArtwork {pictura,sculptura,grafica,fotografie,altul};
    public enum artworkStatus {disponibil,vandut,in_expozitie,indisponibil};
}