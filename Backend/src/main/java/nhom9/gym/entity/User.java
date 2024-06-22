package nhom9.gym.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username")
    private String username;
    @Column(name = "password", length = 512)
    private String password;
//    @Column(name = "gioi_tinh")
//    private char gioiTinh;
    @Column(name = "email")
    private String email;

    @Column(name = "active_number")
    private String activeNumber;

    @Column(name = "is_activated")
    private Boolean isActivated ;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "birthday")
    private Date birthday;

    @Column(name = "avatar", columnDefinition = "LONGTEXT")
    @Lob
    private String avatar; // Ảnh đại diện

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH
    })
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roleList;



    @OneToMany(mappedBy = "user")
    private List<PackRegistration> packRegistrations;

    @OneToMany(mappedBy = "user")
    private List<TrainerRegistration> trainerRegistrations;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<TrainingSlot> trainingSlots;
}
