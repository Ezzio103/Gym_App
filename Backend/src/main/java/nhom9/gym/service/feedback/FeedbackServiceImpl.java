package nhom9.gym.service.feedback;

import nhom9.gym.dao.FeedbackRepository;
import nhom9.gym.dao.UserRepository;
import nhom9.gym.entity.Feedback;
import nhom9.gym.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Feedback createFeedback(Long userId, String content) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setContent(content);
        feedback.setTime(LocalDateTime.now());

        return feedbackRepository.save(feedback);
    }
}
