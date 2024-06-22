package nhom9.gym.dao;

import nhom9.gym.entity.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "equipment")
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
    public Equipment findByEquipmentId(Long id);
}
