package nhom9.gym.mapper;

import nhom9.gym.dto.FeedbackDto;
import nhom9.gym.entity.Feedback;

public class FeedbackMapper {

    public static FeedbackDto feedbackToDto(Feedback feedback) {
        if (feedback == null) {
            return null;
        }

        FeedbackDto dto = new FeedbackDto();
        dto.setUserId(feedback.getUser().getUserId()); // Assuming User has a getter 'getUserId'
        dto.setContent(feedback.getContent());
        dto.setTime(feedback.getTime());

        return dto;
    }

    public static Feedback dtoToFeedback(FeedbackDto dto) {
        if (dto == null) {
            return null;
        }

        Feedback feedback = new Feedback();
        // Assuming User and time are set separately
        // feedback.setUser(...);
        feedback.setContent(dto.getContent());
        feedback.setTime(dto.getTime());

        return feedback;
    }
}
