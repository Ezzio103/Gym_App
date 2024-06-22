package nhom9.gym.service.registration;

import nhom9.gym.entity.Registration;
import org.springframework.http.ResponseEntity;

public interface RegistrationService {
    ResponseEntity<?> register(String type, Long userId, Long packId, int duration, String txnRef);
    long calculateAmount(String type, Long packId, int duration);
}
