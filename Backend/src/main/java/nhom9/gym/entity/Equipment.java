package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long equipmentId;

    @Column(name = "equipment_name")
    private String equipmentName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "purchase_date")
    private Date purchaseDate;

    @Column(name = "warranty_date")
    private Date warrantyDate;

    @Column(name = "origin")
    private String origin;

    @Column(name = "status")
    private String status;
}
