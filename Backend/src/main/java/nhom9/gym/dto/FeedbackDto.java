package nhom9.gym.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FeedbackDto {
    private Long userId;
    private String content;
    private LocalDateTime time;
    // Getters and setters
//    public Long getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    public String getContent() {
//        return content;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
}
