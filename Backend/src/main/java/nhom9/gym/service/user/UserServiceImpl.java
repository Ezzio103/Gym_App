package nhom9.gym.service.user;


import nhom9.gym.dao.RoleRepository;
import nhom9.gym.dao.UserRepository;
import nhom9.gym.entity.Role;
import nhom9.gym.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

//    @Autowired
//    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
//        this.userRepository = userRepository;
//        this.roleRepository = roleRepository;
//    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public ResponseEntity<?> save(User user) {
        User user1 = userRepository.save(user);
        System.out.println(user1.toString());
        return ResponseEntity.ok("ok");
    }
    @Override
    public ResponseEntity<?> delete(Long id) {
        System.out.println("da vao serviceiplm");
        User user = userRepository.findByUserId(id);
        if(user==null){

            return ResponseEntity.badRequest().build();
        }
         userRepository.deleteById(id);
//        System.out.println(user1.toString());
        return ResponseEntity.ok("xoa thanh cong");
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("Tài khoản không tồn tại!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthorities(user.getRoleList()));

    }

    private Collection<? extends GrantedAuthority> rolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
    }
}