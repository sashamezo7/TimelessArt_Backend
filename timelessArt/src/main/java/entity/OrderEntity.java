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
@Table(name = "orders", schema = "timelessart")
public class OrderEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_order")
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_client")
    private ClientsEntity client;
    @OneToMany(mappedBy = "order")
    private List<ArtworkEntity> artworks;
    @Column(name = "order_date", nullable = false)
    private Date orderDate;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private orderStatus status;
    @Column(name = "update_date")
    private Timestamp updateDate;

    public enum orderStatus{in_asteptare,confirmata,expediata,livrata,anulata}


}
