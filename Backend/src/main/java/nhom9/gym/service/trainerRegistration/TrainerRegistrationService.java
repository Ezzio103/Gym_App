package nhom9.gym.service.trainerRegistration;

import nhom9.gym.entity.TrainerRegistration;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TrainerRegistrationService {
//    public ResponseEntity<?> register(TrainerRegistration trainerRegistration);
    public List<TrainerRegistration> currentPack(Long userId);
}
