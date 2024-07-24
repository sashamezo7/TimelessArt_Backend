package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "review", schema = "timelessart", catalog = "")
public class ReviewEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_review")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false)
    private ClientsEntity client;
    @ManyToOne
    @JoinColumn(name = "id_artwork", nullable = false)
    private ArtworkEntity artwork;
    @Basic
    @Column(name = "review_text", nullable = false)
    private String reviewText;
    @Basic
    @Column(name = "rating", nullable = false)
    private Integer rating;
    @Basic
    @Column(name = "review_date", nullable = false)
    private Timestamp reviewDate;


}
