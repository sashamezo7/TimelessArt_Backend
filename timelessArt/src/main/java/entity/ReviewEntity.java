package entity;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "review", schema = "timelessart", catalog = "")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_review")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientsEntity client;
    @ManyToOne
    @JoinColumn(name = "id_artwork")
    private ArtworkEntity artwork;
    @Basic
    @Column(name = "review_text")
    private String reviewText;
    @Basic
    @Column(name = "rating")
    private Integer rating;
    @Basic
    @Column(name = "review_date")
    private Timestamp reviewDate;


    public int getId() {
        return id;
    }

    public String getReviewText() {
        return reviewText;
    }

    public Integer getRating() {
        return rating;
    }

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }

    public ArtworkEntity getArtwork() {
        return artwork;
    }

    public void setArtwork(ArtworkEntity artwork) {
        this.artwork = artwork;
    }
}
