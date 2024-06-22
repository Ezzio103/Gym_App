package nhom9.gym.service.email;


public interface EmailService {
    public void sendMessage(String from, String to, String subject, String text);
}