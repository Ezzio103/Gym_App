package nhom9.gym.service.room;

import nhom9.gym.dao.EquipmentRepository;
import nhom9.gym.dao.RoomRepository;
import nhom9.gym.entity.Equipment;
import nhom9.gym.entity.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService{
    @Autowired
    private RoomRepository roomRepository;
    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Room room = roomRepository.findByRoomId(id);
        if(room==null){
            return ResponseEntity.badRequest().build();
        }
        roomRepository.deleteById(id);
        return ResponseEntity.ok("da xoa room");
    }
}
