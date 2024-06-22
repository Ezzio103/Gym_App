package nhom9.gym.controller;

import nhom9.gym.dto.TrainingSlotDto;
import nhom9.gym.entity.TrainingSlot;
import nhom9.gym.mapper.TrainingSlotMapper;
import nhom9.gym.service.trainingSlot.TrainingSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainingslots")
public class TrainingSlotController {
    @Autowired
    private  TrainingSlotService trainingSlotService;

//    @Autowired
//    private TrainingSlotService trainingSlotService;

    @GetMapping("/available/{trainerId}")
    public List<TrainingSlotDto> getAvailableSlots(@PathVariable Long trainerId) {
        List<TrainingSlot> trainingSlots = trainingSlotService.getAvailableSlotsByTrainerId(trainerId);
        List<TrainingSlotDto> dtos = new ArrayList<>();
        for (TrainingSlot trainingSlot : trainingSlots) {
            dtos.add(TrainingSlotMapper.mapToDTO(trainingSlot));
        }
        return dtos;
    }

    @GetMapping("/registered/{userId}")
    public List<TrainingSlotDto> getRegisteredSlots(@PathVariable Long userId) {
        List<TrainingSlot> trainingSlots = trainingSlotService.getRegisteredSlotsByUserId(userId);
        List<TrainingSlotDto> dtos = new ArrayList<>();
        for (TrainingSlot trainingSlot : trainingSlots) {
            dtos.add(TrainingSlotMapper.mapToDTO(trainingSlot));
        }
        return dtos;
    }

    @PostMapping("/schedule")
    public TrainingSlot scheduleSession(@RequestParam int slotId, @RequestParam Long userId) {

        return trainingSlotService.scheduleSession(slotId, userId);
    }

    @PostMapping("/schedule/multiple")
    public void scheduleMultipleSessions(@RequestBody List<Integer> slotIds, @RequestParam Long userId) {
        trainingSlotService.scheduleMultipleSessions(slotIds, userId);
    }
}

