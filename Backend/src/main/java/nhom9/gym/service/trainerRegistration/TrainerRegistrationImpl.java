package nhom9.gym.service.trainerRegistration;

import nhom9.gym.dao.TrainerRegistrationRepository;
import nhom9.gym.entity.TrainerRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

@Service
public class TrainerRegistrationImpl implements TrainerRegistrationService {

    @Autowired
    private TrainerRegistrationRepository trainerRegistrationRepository;

//    @Override
//    public ResponseEntity<?> register(TrainerRegistration trainerRegistration) {
//        TrainerRegistration result = trainerRegistrationRepository.findTopByUserUserIdAndTrainerTrainerIdOrderByExpiryDateDesc(
//                trainerRegistration.getUser().getUserId(), trainerRegistration.getTrainer().getTrainerId());
//
//        if (result != null) {
//            if (result.getExpiryDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
//                return ResponseEntity.ok("Bạn đã đăng ký huấn luyện viên trước đó");
//            } else {
//                TrainerRegistration newTrainerRegistration = new TrainerRegistration();
//                newTrainerRegistration.setUser(trainerRegistration.getUser());
//                newTrainerRegistration.setTrainer(trainerRegistration.getTrainer());
//                newTrainerRegistration.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
//                newTrainerRegistration.setStatus("0");
//                newTrainerRegistration.setRenewalCount(result.getRenewalCount()+1);
//                newTrainerRegistration.setExpiryDate(LocalDate.from(LocalDateTime.now().plusMonths(1)));
//                trainerRegistrationRepository.save(newTrainerRegistration);
//                return ResponseEntity.ok("Đăng ký thành công");
//            }
//        } else {
//            trainerRegistration.setRegistrationDate(LocalDate.from(LocalDateTime.now()));
//            trainerRegistration.setExpiryDate(LocalDate.from(LocalDateTime.now().plusMonths(1)));
//            trainerRegistration.setStatus("0");
//            trainerRegistration.setRenewalCount(0);
//            trainerRegistrationRepository.save(trainerRegistration);
//            return ResponseEntity.ok("Đăng ký thành công");
//        }
//    }

    @Override
    public List<TrainerRegistration> currentPack(Long userId) {
        return trainerRegistrationRepository.findCurrentRegistrationsByUserId(userId);
    }
}
