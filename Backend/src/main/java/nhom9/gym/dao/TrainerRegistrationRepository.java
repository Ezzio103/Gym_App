package nhom9.gym.dao;

import nhom9.gym.entity.TrainerRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(path = "trainer-registration")
public interface TrainerRegistrationRepository extends JpaRepository<TrainerRegistration, Long> {

    @Query("SELECT tr FROM TrainerRegistration tr WHERE tr.user.userId = :userId AND tr.status = 'success' AND tr.expiryDate >= CURRENT_DATE " +
            "AND tr.expiryDate = (SELECT MAX(tr2.expiryDate) FROM TrainerRegistration tr2 WHERE tr2.user.userId = tr.user.userId AND tr2.trainer.trainerId = tr.trainer.trainerId AND tr2.status = 'success')")
    List<TrainerRegistration> findCurrentRegistrationsByUserId(@Param("userId") Long userId);

    @Query("SELECT MAX(tr.renewalCount) FROM TrainerRegistration tr " +
            "WHERE tr.user.userId = :userId AND tr.trainer.trainerId = :trainerId AND tr.status = 'success'")
    Integer findMaxRenewalCountByTrainerIdAndUserId(@Param("userId") Long userId, @Param("trainerId") Long trainerId);

    @Query("SELECT MAX(tr.expiryDate) FROM TrainerRegistration tr " +
            "WHERE tr.user.userId = :userId AND tr.trainer.trainerId = :trainerId AND tr.status = 'success'")
    LocalDate findMaxExpiryDateByTrainerIdAndUserId(@Param("userId") Long userId, @Param("trainerId") Long trainerId);

    @Query("SELECT COUNT(tr) > 0 " +
            "FROM TrainerRegistration tr " +
            "WHERE tr.user.userId = :userId AND tr.trainer.trainerId = :trainerId AND tr.status = 'success'")
    boolean existsByUserIdAndTrainerIdWithSuccessStatus(@Param("userId") Long userId, @Param("trainerId") Long trainerId);

    @Query("SELECT tr FROM TrainerRegistration tr " +
            "WHERE tr.user.userId = :userId AND tr.trainer.trainerId = :trainerId AND tr.status = 'success' " +
            "ORDER BY tr.expiryDate DESC")
    TrainerRegistration findTopByUserUserIdAndTrainerTrainerIdOrderByExpiryDateDesc(@Param("userId") Long userId, @Param("trainerId") Long trainerId);
}
