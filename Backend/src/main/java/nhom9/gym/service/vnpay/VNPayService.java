package nhom9.gym.service.vnpay;

import jakarta.servlet.http.HttpServletRequest;
import nhom9.gym.config.VNPayConfig;
import nhom9.gym.dao.RegistrationRepository;
import nhom9.gym.entity.Registration;
import nhom9.gym.service.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class VNPayService {
    @Autowired
    private RegistrationRepository registrationRepository;

    public String createPaymentUrl(HttpServletRequest request, long amount, String txnRef) throws UnsupportedEncodingException {
        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", VNPayConfig.vnp_Version);
        vnp_Params.put("vnp_Command", VNPayConfig.vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", "NCB");
        vnp_Params.put("vnp_TxnRef", txnRef);
        vnp_Params.put("vnp_OrderInfo", "info");
        vnp_Params.put("vnp_OrderType", "other");
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", VNPayConfig.getIpAddress(request));

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                // Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                // Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        return VNPayConfig.vnp_PayUrl + "?" + queryUrl;
    }

    public ResponseEntity<String> handlePaymentReturn(Map<String, String> allParams) {
        String vnp_SecureHash = allParams.get("vnp_SecureHash");
        Map<String, String> vnp_Params = new HashMap<>();
        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            if (!entry.getKey().equals("vnp_SecureHash")) {
                vnp_Params.put(entry.getKey(), entry.getValue());
            }
        }

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                hashData.append('&');
            }
        }
        hashData = new StringBuilder(hashData.substring(0, hashData.length() - 1));
        String secureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        boolean isSuccess = secureHash.equals(vnp_SecureHash) && "00".equals(vnp_Params.get("vnp_ResponseCode"));

        String txnRef = vnp_Params.get("vnp_TxnRef");

        // Cập nhật trạng thái thanh toán
        Registration registration =registrationRepository.findByTxnRef(txnRef);
        if (registration != null) {
            registration.setStatus(isSuccess ? "Success" : "Failed");
            registration.setRegistrationDate(LocalDate.now());
            registrationRepository.save(registration);
        }

        // Xử lý kết quả dựa trên isSuccess (true/false)
        if (isSuccess) {
            return ResponseEntity.ok("Thanh toán thành công");
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
