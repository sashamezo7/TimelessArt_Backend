package entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account", schema = "timelessart")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
