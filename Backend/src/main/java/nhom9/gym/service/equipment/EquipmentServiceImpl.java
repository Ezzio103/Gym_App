package nhom9.gym.service.equipment;

import nhom9.gym.dao.EquipmentRepository;
import nhom9.gym.entity.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EquipmentServiceImpl implements EquipmentService{
    @Autowired
    private EquipmentRepository equipmentRepository;
    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Equipment equipment = equipmentRepository.findByEquipmentId(id);
        if(equipment==null){
            return ResponseEntity.badRequest().build();
        }
        equipmentRepository.deleteById(id);
        return ResponseEntity.ok("da xoa equipment");
    }
}
