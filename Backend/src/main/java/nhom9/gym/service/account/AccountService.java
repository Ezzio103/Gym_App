package nhom9.gym.service.account;


import nhom9.gym.dao.UserRepository;
import nhom9.gym.entity.User;
import nhom9.gym.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<?> dangKyNguoiDung(User user){
        // Kiểm tra tên đăng nhập đã tồn tại chưa?
        if(userRepository.existsByUsername(user.getUsername())){
            return ResponseEntity.badRequest().body("ten dang nhap da ton tai");
        }

        // Kiểm tra email đã tồn tại chưa?
        if(userRepository.existsByEmail(user.getEmail())){
            return ResponseEntity.badRequest().body("Email đã tồn tại.");
        }

        // Mã hóa mật khẩu
        String encryptPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptPassword);


        // Gán và gửi thông tin kích hoạt
        user.setActiveNumber(taoMaKichHoat());
        user.setIsActivated(false);

        // Lưu người dùng người dùng vào DB
        User nguoiDung_daDangKy = userRepository.save(user);

        // Gửi email cho người dùng để họ kích hoạt
        guiEmailKichHoat(user.getEmail(), user.getActiveNumber());

        return ResponseEntity.ok("Đăng ký thành công");
    }


    private String taoMaKichHoat(){
        // Tạo mã ngẫu nhiên
        return UUID.randomUUID().toString();
    }

    private void guiEmailKichHoat(String email, String activeNumber){
        String subject = "Kích hoạt tài khoản của bạn tại WebBanSach";
        String text = "Vui lòng sử dụng mã sau để kich hoạt cho tài khoản <"+email+">:<html><body><br/><h1>"+activeNumber+"</h1></body></html>";
        text+="<br/> Click vào đường link để kích hoạt tài khoản: ";
        String url = "http://localhost:3000/account-active/"+email+"/"+activeNumber;
        text+=("<br/> <a href="+url+">"+url+"</a> ");

        emailService.sendMessage("tunletest1.email@gmail.com", email, subject, text);
    }

    public ResponseEntity<?> kichHoatTaiKHoan(String email, String activeNumber) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            return ResponseEntity.badRequest().body("Người dùng không tồn tại!");
        }

        if (user.getIsActivated()) {
            return ResponseEntity.badRequest().body("Tài khoản đã được kích hoạt!");
        }

        if (activeNumber.equals(user.getActiveNumber())) {
            user.setIsActivated(true);
            userRepository.save(user);
            return ResponseEntity.ok("Kích hoạt tài khoản thành công!");
        } else {
            return ResponseEntity.badRequest().body("Mã kích hoạt không chính xác!");
        }
    }
}
