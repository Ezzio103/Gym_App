package nhom9.gym.service.registration;

import nhom9.gym.dao.*;
import nhom9.gym.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private PackRepository packRepository;
    @Autowired
    private TrainerRepository trainerRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PackRegistrationRepository packRegistrationRepository;

    @Autowired
    private TrainerRegistrationRepository trainerRegistrationRepository;

    @Override
    public ResponseEntity<?> register(String type, Long userId, Long packId, int duration, String txnRef) {
        Optional<User> userOptional = userRepository.findById(userId);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid user ID");
        }

        User user = userOptional.get();

        if ("pack".equals(type)) {
            Optional<Pack> packOptional = packRepository.findById(packId);
            if (!packOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid pack ID");
            }

            Pack pack = packOptional.get();
            PackRegistration registration = new PackRegistration();
            registration.setUser(user);
            registration.setPack(pack);
            registration.setRegistrationDate(LocalDate.now());
            registration.setAmount(pack.getPrice() * duration);
            if(packRegistrationRepository.findMaxExpiryDateByPackIdAndUserId(userId, packId) !=null ){
                registration.setExpiryDate(packRegistrationRepository.findMaxExpiryDateByPackIdAndUserId(userId, packId).plusMonths(duration));
            }else {
            registration.setExpiryDate(LocalDate.now().plusMonths(duration));
             }
            Integer maxRenewalCount = packRegistrationRepository.findMaxRenewalCountByPackIdAndUserId(userId, packId);
            registration.setRenewalCount(maxRenewalCount != null ? maxRenewalCount : 0);// Set renewal count to 0 if not found

            registration.setStatus("0"); // Unpaid
            registration.setTxnRef(txnRef);
            packRegistrationRepository.save(registration);

        } else if ("trainer".equals(type)) {
            Optional<Trainer> trainerOptional = trainerRepository.findById(packId);
            if (!trainerOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid trainer ID");
            }

            Trainer trainer = trainerOptional.get();
            TrainerRegistration registration = new TrainerRegistration();
            registration.setUser(user);
            registration.setTrainer(trainer);
            registration.setRegistrationDate(LocalDate.now());
            registration.setAmount(trainer.getPrice() * duration);
            if(trainerRegistrationRepository.findMaxExpiryDateByTrainerIdAndUserId(userId, packId) !=null ){
                registration.setExpiryDate(trainerRegistrationRepository.findMaxExpiryDateByTrainerIdAndUserId(userId, packId).plusMonths(duration));
            }else {
                registration.setExpiryDate(LocalDate.now().plusMonths(duration));
            }
            Integer maxRenewalCount = trainerRegistrationRepository.findMaxRenewalCountByTrainerIdAndUserId(userId, packId);
            registration.setRenewalCount(maxRenewalCount != null ? maxRenewalCount : 0);// Set renewal count to 0 if not found

//            registration.setExpiryDate(trainerRegistrationRepository.findMaxExpiryDateByTrainerIdAndUserId(userId, packId).plusMonths(duration));
//            registration.setRenewalCount(trainerRegistrationRepository.findMaxRenewalCountByTrainerIdAndUserId(userId, packId) + 1);
            registration.setStatus("0"); // Unpaid
            registration.setTxnRef(txnRef);
            trainerRegistrationRepository.save(registration);
        }

        return ResponseEntity.ok("Create successful");
    }

    @Override
    public long calculateAmount(String type, Long packId, int duration) {
        if ("pack".equals(type)) {
            Optional<Pack> packOptional = packRepository.findById(packId);
            if (!packOptional.isPresent()) {
                throw new IllegalArgumentException("Invalid pack ID");
            }

            Pack pack = packOptional.get();
            return (long) (pack.getPrice() * duration );

        } else if ("trainer".equals(type)) {
            Optional<Trainer> trainerOptional = trainerRepository.findById(packId);
            if (!trainerOptional.isPresent()) {
                throw new IllegalArgumentException("Invalid trainer ID");
            }

            Trainer trainer = trainerOptional.get();
            return (long) (trainer.getPrice() * duration );

        } else {
            throw new IllegalArgumentException("Invalid type");
        }
    }
}
