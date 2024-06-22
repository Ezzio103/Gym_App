package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;


@Data
//@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "registration")
public  class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long registrationId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    protected User user;

    @Column(name = "registration_date")
    protected LocalDate registrationDate;

    @Column(name = "expiry_date")
    protected LocalDate expiryDate;

    @Column(name = "amount")
    protected double amount;

    @Column(name = "status")
    protected String status;
        @Column(name = "renewal_count", nullable = false)
    private int renewalCount;
    @Column(name = "txn_ref")
    private String txnRef;

}

