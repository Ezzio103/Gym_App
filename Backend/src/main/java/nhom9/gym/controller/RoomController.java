package nhom9.gym.controller;

import nhom9.gym.entity.PackRegistration;
import nhom9.gym.service.equipment.EquipmentServiceImpl;
import nhom9.gym.service.pack.PackageServiceImpl;
import nhom9.gym.service.packRegistration.PackRegistrationImpl;
import nhom9.gym.service.room.RoomService;
import nhom9.gym.service.room.RoomServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rooms")
public class RoomController {
    @Autowired
    private RoomService roomService;
    @DeleteMapping("/delete")
    public ResponseEntity<?> register(@RequestParam Long roomId){
        return  roomService.deleteById(roomId);
    }
}
