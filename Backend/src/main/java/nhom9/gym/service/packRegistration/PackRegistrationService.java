package nhom9.gym.service.packRegistration;

import nhom9.gym.entity.Pack;
import nhom9.gym.entity.PackRegistration;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface PackRegistrationService {
    public ResponseEntity<?> register(PackRegistration packRegistration);
    public List<PackRegistration> currentPack(Long userId);
}
