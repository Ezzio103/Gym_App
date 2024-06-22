package nhom9.gym.dao;


import nhom9.gym.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "role")
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByRoleName(String roleName);
}
