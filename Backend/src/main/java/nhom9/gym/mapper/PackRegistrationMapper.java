package nhom9.gym.mapper;

import nhom9.gym.dto.PackRegistrationDto;
import nhom9.gym.entity.PackRegistration;

public class PackRegistrationMapper {
    public static PackRegistrationDto mapToDTO(PackRegistration packRegistration) {
        if (packRegistration == null) {
            return null;
        }

        PackRegistrationDto dto = new PackRegistrationDto();
        dto.setRegistrationId(packRegistration.getRegistrationId());
        dto.setUserId(packRegistration.getUser().getUserId()); // Assuming User has a getter 'getId'
        dto.setPackId(packRegistration.getPack().getPackId()); // Assuming Pack has a getter 'getId'
        dto.setRegistrationDate(packRegistration.getRegistrationDate());
        dto.setExpiryDate(packRegistration.getExpiryDate());
        dto.setStatus(packRegistration.getStatus());
        dto.setTxnRef(packRegistration.getTxnRef());
        dto.setRenewalCount(packRegistration.getRenewalCount());

        return dto;
    }
    public static PackRegistration mapToEntity(PackRegistrationDto dto) {
        if (dto == null) {
            return null;
        }

        PackRegistration packRegistration = new PackRegistration();
        packRegistration.setRegistrationId(dto.getRegistrationId());

        // Assuming User and Pack objects are retrieved elsewhere (e.g., by ID)
        // packRegistration.setUser(...);
        // packRegistration.setPack(...);

        packRegistration.setRegistrationDate(dto.getRegistrationDate());
        packRegistration.setExpiryDate(dto.getExpiryDate());
        packRegistration.setStatus(dto.getStatus());
        packRegistration.setTxnRef(dto.getTxnRef());
        packRegistration.setRenewalCount(dto.getRenewalCount());

        return packRegistration;
    }
}
