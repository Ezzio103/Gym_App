package nhom9.gym.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data


public class TrainingSlotDto {

    private int slotId;
    private Long trainerId;
    private String trainerName; // Added trainerName for improved representation
    private Long userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;


}