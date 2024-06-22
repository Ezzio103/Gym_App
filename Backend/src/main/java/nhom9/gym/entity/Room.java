package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "room")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "description")
    private String description;
}
