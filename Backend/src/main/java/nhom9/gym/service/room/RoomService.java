package nhom9.gym.service.room;

import org.springframework.http.ResponseEntity;

public interface RoomService {
    public ResponseEntity<?> deleteById(Long Id);
}
