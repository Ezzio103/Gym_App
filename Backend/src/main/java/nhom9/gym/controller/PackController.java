package nhom9.gym.controller;

import nhom9.gym.entity.PackRegistration;
import nhom9.gym.service.pack.PackageService;
import nhom9.gym.service.pack.PackageServiceImpl;
import nhom9.gym.service.packRegistration.PackRegistrationImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/packs")
public class PackController {

    @Autowired
    private PackageService packageService;
    @DeleteMapping("/delete")
    public ResponseEntity<?> register(@RequestParam Long packId){
        return  packageService.deleteById(packId);
    }
}
