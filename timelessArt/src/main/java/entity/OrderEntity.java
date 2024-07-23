package entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "orders", schema = "timelessart", catalog = "")
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
    @Basic
    @Column(name = "order_date")
    private Date orderDate;
    @Basic
    @Column(name = "price")
    private BigDecimal price;
    @Basic
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private orderStatus status;
    @Basic
    @Column(name = "update_date")
    private Timestamp updateDate;


    public enum orderStatus{in_asteptare,confirmata,expediata,livrata,anulata}

    public int getId() {
        return id;
    }


    public Date getOrderDate() {
        return orderDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public orderStatus getStatus() {
        return status;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(orderStatus status) {
        this.status = status;
    }

    public void setUpdateDate(Timestamp updateDate) {
        this.updateDate = updateDate;
    }

    public ClientsEntity getClient() {
        return client;
    }

    public void setClient(ClientsEntity client) {
        this.client = client;
    }

    public List<ArtworkEntity> getArtworks() {
        return artworks;
    }

    public void setArtworks(List<ArtworkEntity> artworks) {
        this.artworks = artworks;
    }
}
