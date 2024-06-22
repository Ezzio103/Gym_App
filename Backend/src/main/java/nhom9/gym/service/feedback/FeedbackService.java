package nhom9.gym.service.feedback;

import nhom9.gym.entity.Feedback;

public interface FeedbackService {
    Feedback createFeedback(Long userId, String content);
}
