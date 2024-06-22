package nhom9.gym.service.trainingSlot;

import nhom9.gym.entity.TrainingSlot;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TrainingSlotService {


    List<TrainingSlot> getAvailableSlotsByTrainerId(Long trainerId);


    List<TrainingSlot> getRegisteredSlotsByUserId(Long userId);


    TrainingSlot scheduleSession(int slotId, Long userId) ;

    void scheduleMultipleSessions(List<Integer> slotIds, Long userId);
}
