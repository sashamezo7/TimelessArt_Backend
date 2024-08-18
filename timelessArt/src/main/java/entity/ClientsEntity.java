package entity;

import io.quarkus.arc.All;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients", schema = "timelessart", catalog = "")
public class ClientsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_client")
    private int id;
    @OneToOne
    @JoinColumn(name = "account", nullable = false)
    private AccountEntity account;

    @OneToMany(mappedBy = "client")
    private List <ReviewEntity> reviews;

    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orders;

    @Basic
    @Column(name = "name", nullable = false)
    private String name;
    @Basic
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Basic
    @Column(name = "phone")
    private String phone;
    @Basic
    @Column(name = "address")
    private String address;
    @Basic
    @Column(name = "city")
    private String city;
    @Basic
    @Column(name = "postal_code")
    private String postalCode;
    @Basic
    @Column(name = "country")
    private String country;
    @Basic
    @Column(name = "birth_date")
    private Date birthDate;
    @Basic
    @Column(name = "registration_date")
    private Timestamp registrationDate;
    @PrePersist
    protected void onCreate() {
        registrationDate = new Timestamp(System.currentTimeMillis());
    }


}
