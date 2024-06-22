package nhom9.gym.service.equipment;

import org.springframework.http.ResponseEntity;

public interface EquipmentService {
    public ResponseEntity<?> deleteById(Long Id);
}
