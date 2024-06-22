package nhom9.gym.dao;

import nhom9.gym.entity.Pack;
import nhom9.gym.entity.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "trainer")
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Trainer findByEmail(String email);
    public Trainer findByTrainerId(Long id);
    @Query("SELECT t FROM Trainer t WHERE t.name LIKE %:name%")
    public List<Trainer> findByNameContaining(@Param("name") String name);
}
