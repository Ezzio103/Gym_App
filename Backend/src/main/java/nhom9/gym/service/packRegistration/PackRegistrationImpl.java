package nhom9.gym.service.packRegistration;

import nhom9.gym.dao.PackRegistrationRepository;
import nhom9.gym.entity.PackRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class PackRegistrationImpl implements PackRegistrationService {

    @Autowired
    private PackRegistrationRepository packRegistrationRepository;

    @Override
    public ResponseEntity<?> register(PackRegistration packRegistration) {
        PackRegistration result = packRegistrationRepository.findTopByUserUserIdAndPackPackIdOrderByExpiryDateDesc(
                packRegistration.getUser().getUserId(), packRegistration.getPack().getPackId());

        if (result != null) {
            if (result.getExpiryDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
                return ResponseEntity.ok("Bạn đã đăng ký gói trước đó");
            } else {
                // Tạo bản ghi mới khi đăng ký cũ đã hết hạn
                PackRegistration newPackRegistration = new PackRegistration();
                newPackRegistration.setUser(packRegistration.getUser());
                newPackRegistration.setPack(packRegistration.getPack());
                newPackRegistration.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
                newPackRegistration.setStatus("0");
                newPackRegistration.setRenewalCount(result.getRenewalCount()+1);
                // Cập nhật lại expiryDate
                newPackRegistration.setExpiryDate(LocalDate.from(LocalDateTime.now().plusMonths(1))); // Giả sử thời hạn là 1 tháng
                packRegistrationRepository.save(newPackRegistration);
                return ResponseEntity.ok("Đăng ký thành công");
            }
        } else {
            // Tạo bản ghi mới khi không có đăng ký nào trước đó
            packRegistration.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
            // Cập nhật expiryDate
            packRegistration.setExpiryDate(LocalDate.from(LocalDateTime.now().plusMonths(1))); // Giả sử thời hạn là 1 tháng
            packRegistration.setStatus("0");
            packRegistration.setRenewalCount(0);
            packRegistrationRepository.save(packRegistration);
            return ResponseEntity.ok("Đăng ký thành công");
        }
    }

    @Override
    public List<PackRegistration> currentPack(Long userId) {
        List<PackRegistration> registrations = packRegistrationRepository.findCurrentRegistrationsByUserId(userId);
        // Lọc các gói tập có ngày hết hạn nhỏ hơn hôm nay
//        registrations.removeIf(registration -> registration.getExpiryDate().isBefore(LocalDate.now()));
        return registrations;
    }
}
