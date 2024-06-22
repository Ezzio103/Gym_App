package nhom9.gym.dao;



import nhom9.gym.entity.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "pack")
public interface PackRepository extends JpaRepository<Pack,Long> {


    @Query("SELECT p FROM Pack p WHERE p.packName LIKE %:packName%")
    public List<Pack> findByPackNameContaining(@Param("packName") String packName);

    public Pack findByPackId(@Param("packId") Long Id);


}
