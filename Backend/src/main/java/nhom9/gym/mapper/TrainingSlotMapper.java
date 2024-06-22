package nhom9.gym.mapper;

import nhom9.gym.dao.TrainerRepository;
import nhom9.gym.dao.UserRepository;
import nhom9.gym.dto.TrainingSlotDto;
import nhom9.gym.entity.Trainer;
import nhom9.gym.entity.TrainingSlot;
import nhom9.gym.entity.User;
import nhom9.gym.service.trainer.TrainerServiceImpl;
import nhom9.gym.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class TrainingSlotMapper {



    public static TrainingSlotDto mapToDTO(TrainingSlot trainingSlot) {

        if (trainingSlot == null) {
            return null;
        }
        TrainingSlotDto dto = new TrainingSlotDto();
        dto.setSlotId(trainingSlot.getSlotId());
        dto.setTrainerId(trainingSlot.getTrainer().getTrainerId()); // Assuming 'getId' is the actual getter in Trainer
        dto.setTrainerName(trainingSlot.getTrainer().getName()); // Assuming Trainer has a getter 'getName' for better representation
//        dto.setUserId(trainingSlot.getUser().getUserId()); // Assuming 'getId' is the actual getter in User
        if (trainingSlot.getUser() != null) {
            dto.setUserId(trainingSlot.getUser().getUserId());
        } else {
            // Handle the case where user is null, possibly set a default value or throw an exception
            dto.setUserId(null); // or handle appropriately
        }
        dto.setStartTime(trainingSlot.getStartTime());
        dto.setEndTime(trainingSlot.getEndTime());
        return dto;
    }

    public static TrainingSlot mapToEntity(TrainingSlotDto dto) {
        if (dto == null) {
            return null;
        }
        TrainingSlot trainingSlot = new TrainingSlot();
        trainingSlot.setSlotId(dto.getSlotId());
        // Assuming Trainer and User objects are retrieved elsewhere (e.g., by ID)
        trainingSlot.setTrainer(findTrainerById(dto.getTrainerId())); // Implement logic to find Trainer by ID
        trainingSlot.setUser(findUserById(dto.getUserId())); // Implement logic to find User by ID
        trainingSlot.setStartTime(dto.getStartTime());
        trainingSlot.setEndTime(dto.getEndTime());
        return trainingSlot;
    }

    // Implement methods to find Trainer and User by ID (replace with your actual logic)
    private static Trainer findTrainerById(Long trainerId) {
      TrainerServiceImpl trainerService = new TrainerServiceImpl();

        // Replace with your logic to find Trainer by ID
        // This could involve a database call or other retrieval mechanism
        return trainerService.findById(trainerId); // Implement logic to return the Trainer object
    }

    private static User findUserById(Long userId) {
        UserServiceImpl userService = new UserServiceImpl();
        // Replace with your logic to find User by ID
        // This could involve a database call or other retrieval mechanism
        return userService.findById(userId); // Implement logic to return the User object
    }
}
