package nhom9.gym.controller;

import jakarta.servlet.http.HttpServletRequest;
import nhom9.gym.config.VNPayConfig;
import nhom9.gym.entity.Pack;
import nhom9.gym.entity.PackRegistration;
import nhom9.gym.entity.User;
import nhom9.gym.dao.PackRegistrationRepository;
import nhom9.gym.dao.PackRepository;
import nhom9.gym.dao.UserRepository;
import nhom9.gym.service.registration.RegistrationService;
import nhom9.gym.service.vnpay.VNPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("/vnpay")
@CrossOrigin("*")
public class VNPayController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private VNPayService vnPayService;

    @PostMapping("/create-payment")
    public ResponseEntity<?> createPayment(HttpServletRequest request, @RequestParam("userId") Long userId, @RequestParam("id") Long id, @RequestParam("duration") int duration,@RequestParam("type") String type) throws UnsupportedEncodingException {

        long amount = registrationService.calculateAmount(type,id,duration) * 100;

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);

         registrationService.register(type,userId,id,duration,vnp_TxnRef);


        String paymentUrl = vnPayService.createPaymentUrl(request,amount,vnp_TxnRef);


        return ResponseEntity.status(HttpStatus.OK).body(paymentUrl);
    }

@GetMapping("/return")
public ResponseEntity<?> paymentReturn(@RequestParam Map<String, String> allParams) {
    return vnPayService.handlePaymentReturn(allParams);
}
}
