package nhom9.gym.dao;


import nhom9.gym.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "user")
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByUserId(Long userId);
    public User findByEmail(String email);
    public User findByUsername(String username);

    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);

//    public User deleteById(Long id);
}
