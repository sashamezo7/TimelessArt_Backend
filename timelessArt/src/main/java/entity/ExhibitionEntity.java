package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exhibition", schema = "timelessart")
public class ExhibitionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_exhibition")
    private int id;
    @ManyToMany(mappedBy = "exhibitions")
    private List<ArtistEntity> artists;
    @OneToMany(mappedBy = "exhibition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArtworkEntity> artworks;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "location")
    private String location;
    @Basic
    @Column(name = "start_date")
    private Date startDate;
    @Basic
    @Column(name = "end_date")
    private Date endDate;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private exhibitionStatus status;

    @Column(name = "picture_url")
    private String pictureUrl;


    public enum exhibitionStatus{planificata,indesfasurare,finalizata}


}
