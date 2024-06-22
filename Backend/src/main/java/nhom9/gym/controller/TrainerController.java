package nhom9.gym.controller;

import nhom9.gym.entity.PackRegistration;
import nhom9.gym.service.equipment.EquipmentServiceImpl;
import nhom9.gym.service.pack.PackageServiceImpl;
import nhom9.gym.service.packRegistration.PackRegistrationImpl;
import nhom9.gym.service.room.RoomServiceImpl;
import nhom9.gym.service.trainer.TrainerService;
import nhom9.gym.service.trainer.TrainerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainers")
public class TrainerController {
    @Autowired
    private TrainerService trainerService;
    @DeleteMapping("/delete")
    public ResponseEntity<?> delete(@RequestParam Long trainerId){
        return  trainerService.deleteById(trainerId);
    }
}
