package nhom9.gym.service.trainer;

import nhom9.gym.dao.TrainerRepository;
import nhom9.gym.entity.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TrainerServiceImpl implements TrainerService{
    @Autowired
    private TrainerRepository trainerRepository;
    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Trainer trainer = trainerRepository.findByTrainerId(id);
        if(trainer==null){
            return ResponseEntity.badRequest().build();
        }
        trainerRepository.deleteById(id);
        return ResponseEntity.ok("đã xóa trainer thành công");
    }

    @Override
    public Trainer findById(Long id) {
        return trainerRepository.findByTrainerId(id);
    }
}
