package nhom9.gym.controller;

import nhom9.gym.dto.PackRegistrationDto;
import nhom9.gym.entity.PackRegistration;
import nhom9.gym.entity.User;
import nhom9.gym.mapper.PackRegistrationMapper;
import nhom9.gym.service.packRegistration.PackRegistrationImpl;
import nhom9.gym.service.packRegistration.PackRegistrationService;
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
@RequestMapping("/pack-registrations")
public class PackRegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private PackRegistrationService packRegistrationService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PackRegistration packRegistration){
        return  packRegistrationService.register(packRegistration);
    }
    @GetMapping("/current/{userId}")
    public ResponseEntity<?> getCurrentPack(@PathVariable Long userId) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUserId = userDetails.getUsername();
        User user = userService.findById(userId);

        if (!currentUserId.equals(user.getUsername())
                && !userDetails.getAuthorities().contains("ADMIN") && !userDetails.getAuthorities().contains("STAFF")
        ) {
            System.out.println("xác thực lỗi");
            if(!currentUserId.equals(user.getUsername())){
                System.out.println("lỗi do ko trùng userid");
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access Denied");
//            System.out.println("xác thực ok");
        }

        List<PackRegistration> currentPacks = packRegistrationService.currentPack(userId);
//        System.out.println(currentPacks.toString());
//        System.out.println(currentPacks);
        List<PackRegistrationDto> dtos = new ArrayList<>();
        for (PackRegistration packRegistration : currentPacks) {
            dtos.add(PackRegistrationMapper.mapToDTO(packRegistration)); // Replace with your mapping logic
        }

        return ResponseEntity.ok(dtos);
    }

    // Helper method to convert PackRegistration to PackRegistrationDTO (optional with utility class)
//    private List<PackRegistrationDTO> convertPackRegistrationsToDTOs(List<PackRegistration> packRegistrations) {
//        List<PackRegistrationDTO> dtos = new ArrayList<>();
//        for (PackRegistration packRegistration : packRegistrations) {
//            dtos.add(PackRegistrationMapper.mapToDTO(packRegistration)); // Replace with your mapping logic
//        }
//        return dtos;
//    }

}
