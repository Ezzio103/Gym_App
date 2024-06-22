package nhom9.gym.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackRegistrationDto {

    private Long registrationId;
    private Long userId;
    private Long packId;
    private LocalDate registrationDate;
    private LocalDate expiryDate;
    private String status;
    private String txnRef;
    private int renewalCount;

}
