package entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "exhibition", schema = "timelessart", catalog = "")
public class ExhibitionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_exhibition")
    private int id;
    @ManyToMany(mappedBy = "exhibitions")
    private List<ArtistEntity> artists;
    @OneToMany(mappedBy = "exhibition")
    private List <ArtworkEntity> artworks;
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


    public enum exhibitionStatus{planificata,indesfasurare,finalizata}

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getDescription() {
        return description;
    }

    public exhibitionStatus getStatus() {
        return status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(exhibitionStatus status) {
        this.status = status;
    }

    public List<ArtistEntity> getArtists() {
        return artists;
    }

    public List<ArtworkEntity> getArtworks() {
        return artworks;
    }

    public void setArtists(List<ArtistEntity> artists) {
        this.artists = artists;
    }

    public void setArtworks(List<ArtworkEntity> artworks) {
        this.artworks = artworks;
    }
}
