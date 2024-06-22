package nhom9.gym.dao;

import nhom9.gym.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "feedback")
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
