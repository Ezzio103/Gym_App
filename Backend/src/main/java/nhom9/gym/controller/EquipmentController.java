package nhom9.gym.controller;

import nhom9.gym.entity.PackRegistration;
import nhom9.gym.service.equipment.EquipmentService;
import nhom9.gym.service.equipment.EquipmentServiceImpl;
import nhom9.gym.service.pack.PackageServiceImpl;
import nhom9.gym.service.packRegistration.PackRegistrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/equipments")
public class EquipmentController {
   @Autowired
   private EquipmentService equipmentService;
    @DeleteMapping("/delete")
    public ResponseEntity<?> register(@RequestParam Long equipmentId){
        return  equipmentService.deleteById(equipmentId);
    }
}
