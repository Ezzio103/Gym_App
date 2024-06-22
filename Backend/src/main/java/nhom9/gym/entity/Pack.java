package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Entity
@Data
@Table(name = "pack")
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pack_id")
    private Long packId;

    @Column(name = "pack_name")
    private String packName;



    @Column(name = "pack_info")
    private String packInfo;

    @Column(name = "price")
    private double price;


    @Column(name = "image", columnDefinition = "LONGTEXT")
    @Lob
    private String image; // Ảnh đại diện

    @OneToMany(mappedBy = "pack")
    private List<PackRegistration> registrations;





}
