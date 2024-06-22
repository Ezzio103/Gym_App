package nhom9.gym.controller;


import nhom9.gym.entity.User;
import nhom9.gym.security.JwtResponse;
import nhom9.gym.security.LoginRequest;
import nhom9.gym.service.Jwt.JwtService;
import nhom9.gym.service.account.AccountService;
import nhom9.gym.service.user.UserService;
import nhom9.gym.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    @PostMapping("/signup")
    public ResponseEntity<?> dangKyNguoiDung (@Validated @RequestBody User user){
                ResponseEntity<?> response = accountService.dangKyNguoiDung(user);
        return response;
    }
    @GetMapping("/active")
    public ResponseEntity<?> dangKyNguoiDung ( @RequestParam String email, @RequestParam String activeNumber){

        ResponseEntity<?> response = accountService.kichHoatTaiKHoan(email,activeNumber);
        return response;
    }
    @PostMapping("/login")
    public ResponseEntity<?> dangNhap(@RequestBody LoginRequest loginRequest){

        // Xác thực người dùng bằng tên đăng nhập và mật khẩu
        try {

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            // Nếu xác thực thành công, tạo token JWT
            if(authentication.isAuthenticated()){

                final String jwt = jwtService.generateToken(loginRequest.getUsername());
                return ResponseEntity.ok(new JwtResponse(jwt));
            }
        }catch (AuthenticationException e){

            // Xác thực không thành công, trả về lỗi hoặc thông báo
            return ResponseEntity.badRequest().body("Tên đăng nhập hặc mật khẩu không chính xác.");
        }
        return ResponseEntity.badRequest().body("Xác thực không thành công.");
    }
}
