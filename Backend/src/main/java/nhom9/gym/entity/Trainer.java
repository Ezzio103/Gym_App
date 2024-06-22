package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "trainer")
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trainerId;

    @Column(name = "name")
    private String name;

    @Column(name = "experience")
    private String experience;

    @Column(name = "phone")
    private String phone;

//  unique = true cho email
    @Column(name = "email")
    private String email;

    @Column(name = "avatar", columnDefinition = "LONGTEXT")
    @Lob
    private String avatar; // Ảnh đại diện

    @Column(name = "price")
    private double price;

    @Column(name = "status")
    private int status; // "active", "inactive"

    @OneToMany(mappedBy = "trainer")
    private List<TrainingSlot> trainingSlots;

    @OneToMany(mappedBy = "trainer")
    private List<TrainerRegistration> trainerRegistrations;
}
