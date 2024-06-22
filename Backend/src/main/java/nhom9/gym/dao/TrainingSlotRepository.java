package nhom9.gym.dao;

import nhom9.gym.entity.TrainingSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(path = "training-slot")
public interface TrainingSlotRepository extends JpaRepository<TrainingSlot, Integer> {
    // Custom query methods if needed
    @Query("SELECT ts FROM TrainingSlot ts WHERE ts.trainer.trainerId = :trainerId AND ts.user IS NULL AND ts.startTime >= CURRENT_DATE ")
    List<TrainingSlot> findByTrainerTrainerIdAndUserIsNull(@Param("trainerId") Long trainerId);

    @Query("SELECT ts FROM TrainingSlot ts WHERE ts.user.userId = :userId AND ts.startTime >= CURRENT_DATE ")
    List<TrainingSlot> findByUserUserId(@Param("userId") Long userId);

}

