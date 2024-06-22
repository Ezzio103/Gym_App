package nhom9.gym.service.trainingSlot;

import nhom9.gym.dao.TrainingSlotRepository;
import nhom9.gym.entity.TrainingSlot;
import nhom9.gym.entity.User;
import nhom9.gym.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrainingSlotServiceImpl implements TrainingSlotService {

    @Autowired
    private TrainingSlotRepository trainingSlotRepository;
    @Autowired
    private UserService userService;

    @Override
    public List<TrainingSlot> getAvailableSlotsByTrainerId(Long trainerId) {
        return trainingSlotRepository.findByTrainerTrainerIdAndUserIsNull(trainerId);
    }
    @Override
    public List<TrainingSlot> getRegisteredSlotsByUserId(Long userId) {
        return trainingSlotRepository.findByUserUserId(userId);
    }
    @Override
    public TrainingSlot scheduleSession(int slotId, Long userId) {
        TrainingSlot slot = trainingSlotRepository.findById(slotId).orElseThrow();
        User user = userService.findById(userId);
        slot.setUser(user);
        return trainingSlotRepository.save(slot);
    }
    @Override
    public void scheduleMultipleSessions(List<Integer> slotIds, Long userId) {
        slotIds.forEach(slotId -> scheduleSession(slotId, userId));
    }
}
