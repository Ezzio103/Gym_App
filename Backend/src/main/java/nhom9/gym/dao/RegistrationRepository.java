package nhom9.gym.dao;

import nhom9.gym.entity.PackRegistration;
import nhom9.gym.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
//import org.springframework.stereotype.Repository;

@RepositoryRestResource(path = "registration")
public interface RegistrationRepository extends JpaRepository<Registration,Long> {
    public Registration findByTxnRef(String txnRef);


    @Query("SELECT SUM(r.amount) FROM Registration r WHERE r.registrationDate BETWEEN :startDate AND :endDate AND r.status = 'success'")
    Double calculateRevenueInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(r) FROM Registration r WHERE r.registrationDate BETWEEN :startDate AND :endDate AND r.status = 'success' AND r.renewalCount > 0")
    Integer countRegistrationsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT COUNT(r) FROM Registration r WHERE r.registrationDate BETWEEN :startDate AND :endDate AND r.status = 'success' AND r.renewalCount = 0")
    Integer countRenewalsInPeriod(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);


}
