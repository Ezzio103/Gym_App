package nhom9.gym.service.pack;

import nhom9.gym.entity.Pack;
import org.springframework.http.ResponseEntity;

public interface PackageService {
    public Pack findByPackageId(Long id);
    public ResponseEntity<?> deleteById(Long id);
}
