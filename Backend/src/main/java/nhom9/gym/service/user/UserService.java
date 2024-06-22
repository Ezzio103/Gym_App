package nhom9.gym.service.user;


import com.fasterxml.jackson.databind.JsonNode;
import nhom9.gym.entity.User;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    public User findByEmail(String email);
    public User findById(Long id);
    public User findByUsername(String username);
    public ResponseEntity<?> save(User user);
    public ResponseEntity<?> delete(Long id);

}