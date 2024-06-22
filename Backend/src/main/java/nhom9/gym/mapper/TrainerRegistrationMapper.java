package nhom9.gym.mapper;

import nhom9.gym.dto.TrainerRegistrationDto;
import nhom9.gym.entity.TrainerRegistration;

public class TrainerRegistrationMapper {
    public static TrainerRegistrationDto mapToDTO(TrainerRegistration trainerRegistration) {
        if (trainerRegistration == null) {
            return null;
        }

        TrainerRegistrationDto dto = new TrainerRegistrationDto();
        dto.setRegistrationId(trainerRegistration.getRegistrationId());
        dto.setUserId(trainerRegistration.getUser().getUserId());
        dto.setTrainerId(trainerRegistration.getTrainer().getTrainerId());
        dto.setRegistrationDate(trainerRegistration.getRegistrationDate());
        dto.setExpiryDate(trainerRegistration.getExpiryDate());
        dto.setStatus(trainerRegistration.getStatus());
        dto.setRenewalCount(trainerRegistration.getRenewalCount());

        return dto;
    }
}
