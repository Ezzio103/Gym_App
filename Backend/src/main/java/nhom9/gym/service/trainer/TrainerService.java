package nhom9.gym.service.trainer;

import nhom9.gym.entity.Trainer;
import nhom9.gym.entity.User;
import org.springframework.http.ResponseEntity;

public interface TrainerService {
    public ResponseEntity<?> deleteById(Long id);
    public Trainer findById(Long id);
}
