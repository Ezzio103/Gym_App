package nhom9.gym.controller;

import nhom9.gym.dto.TrainerRegistrationDto;
import nhom9.gym.entity.TrainerRegistration;
import nhom9.gym.entity.User;
import nhom9.gym.mapper.TrainerRegistrationMapper;
import nhom9.gym.service.trainerRegistration.TrainerRegistrationImpl;
import nhom9.gym.service.trainerRegistration.TrainerRegistrationService;
import nhom9.gym.service.user.UserService;
import nhom9.gym.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/trainer-registrations")
public class TrainerRegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private TrainerRegistrationService trainerRegistrationService;

//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody TrainerRegistration trainerRegistration){
//        return trainerRegistrationService.register(trainerRegistration);
//    }

    @GetMapping("/current/{userId}")
    public ResponseEntity<?> getCurrentTrainer(@PathVariable Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = userDetails.getUsername();
        User user = userService.findById(userId);

        if (!currentUserId.equals(user.getUsername())
                && !userDetails.getAuthorities().contains("ADMIN") && !userDetails.getAuthorities().contains("STAFF")
        ) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
        }

        List<TrainerRegistration> currentTrainers = trainerRegistrationService.currentPack(userId);
        List<TrainerRegistrationDto> dtos = new ArrayList<>();
        for (TrainerRegistration trainerRegistration : currentTrainers) {
            dtos.add(TrainerRegistrationMapper.mapToDTO(trainerRegistration));
        }

        return ResponseEntity.ok(dtos);
    }
}
