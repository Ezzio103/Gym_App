package nhom9.gym.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "trainer_registration")
public class TrainerRegistration extends Registration  {



    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private Trainer trainer;


}