package nhom9.gym.dao;

import nhom9.gym.entity.PackRegistration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(path = "pack-registration")
public interface PackRegistrationRepository extends JpaRepository<PackRegistration, Long> {
    public PackRegistration findByTxnRef(String txnRef);

    public PackRegistration findTopByUserUserIdAndPackPackIdOrderByExpiryDateDesc(@Param("userId") Long userId, @Param("packId") Long packId);
    @Query("SELECT pr FROM PackRegistration pr WHERE pr.user.userId = :userId AND pr.status = 'success' AND pr.expiryDate >= CURRENT_DATE " +
            "AND pr.expiryDate = (SELECT MAX(pr2.expiryDate) FROM PackRegistration pr2 WHERE pr2.user.userId = pr.user.userId AND pr2.pack.packId = pr.pack.packId AND pr2.status = 'success')")
    List<PackRegistration> findCurrentRegistrationsByUserId(@Param("userId") Long userId);

@Query("SELECT COUNT(pr) > 0 " +
        "FROM PackRegistration pr " +
        "WHERE pr.user.userId = :userId AND pr.pack.packId = :packId AND pr.status = 'success'")
boolean existsByUserIdAndPackIdWithSuccessStatus(@Param("userId") Long userId, @Param("packId") Long packId);
    @Query("SELECT MAX(pr.renewalCount) FROM PackRegistration pr " +
            "WHERE pr.user.userId = :userId AND pr.pack.packId = :packId AND pr.status = 'success'")
    Integer findMaxRenewalCountByPackIdAndUserId(@Param("userId") Long userId, @Param("packId") Long packId);
    @Query("SELECT MAX(pr.expiryDate) FROM PackRegistration pr " +
            "WHERE pr.user.userId = :userId AND pr.pack.packId = :packId AND pr.status = 'success'")
    LocalDate findMaxExpiryDateByPackIdAndUserId(@Param("userId") Long userId, @Param("packId") Long packId);
//    @Query("SELECT COUNT(pr) FROM PackRegistration pr WHERE pr.registrationDate BETWEEN :startDate AND :endDate AND pr.status = 'success' AND pr.renewalCount > 0")
//    Integer countRegistrationsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
//
//    @Query("SELECT COUNT(pr) FROM PackRegistration pr WHERE pr.registrationDate BETWEEN :startDate AND :endDate AND pr.status = 'success' AND pr.renewalCount = 0")
//    Integer countRenewalsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

}
