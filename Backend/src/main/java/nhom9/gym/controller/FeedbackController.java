package nhom9.gym.controller;

import nhom9.gym.dto.FeedbackDto;
import nhom9.gym.entity.Feedback;
import nhom9.gym.service.feedback.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestBody FeedbackDto feedbackDto) {
        Feedback feedback = feedbackService.createFeedback(feedbackDto.getUserId(), feedbackDto.getContent());
        return ResponseEntity.ok(feedback);
    }
}
