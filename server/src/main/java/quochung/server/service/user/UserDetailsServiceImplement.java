package quochung.server.service.user;

import java.util.Set;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import quochung.server.payload.user.UserDetailDto;
import quochung.server.payload.user.UserListElementDto;
import quochung.server.payload.user.UserProfileDto;
import quochung.server.model.user.Role;
import quochung.server.model.user.User;
import quochung.server.repository.user.RoleRepository;
import quochung.server.repository.user.UserRepository;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Không tìm thấy người dùng với tên đăng nhập: " + username));

        return new UserDetailsImplement(user);
    }

    public Long getCurrentUserId() {
        return ((UserDetailsImplement) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId())
                .orElseThrow(
                        () -> new UsernameNotFoundException("Không tìm thấy người dùng với id: " + getCurrentUserId()));
    }

    public UserProfileDto getUserProfile() {
        User user = getCurrentUser();
        return new UserProfileDto(user.getFullName(), user.getBirthday(), user.getEmail(), user.getPhone(),
                user.getGender());
    }

    public void updateUserProfile(UserProfileDto userProfile) {
        User user = getCurrentUser();
        user.setFullName(userProfile.getFullName());
        user.setBirthday(userProfile.getBirthday());
        user.setEmail(userProfile.getEmail());
        user.setPhone(userProfile.getPhone());
        user.setGender(userProfile.getGender());
        userRepository.save(user);
    }

    public void updatePassword(String newPassword) {
        User user = getCurrentUser();
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    public void deleteUser() {
        userRepository.deleteById(getCurrentUserId());
    }

    public List<UserListElementDto> getAllUsers() {
        List<UserListElementDto> userList = userRepository.findAll().stream()
                .map(user -> new UserListElementDto(user.getId(), user.getRoles(), user.getFullName(),
                        user.getGender()))
                .toList();
        return userList;
    }

    public UserDetailDto getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với id: " + userId));
        return new UserDetailDto(user.getId(), user.getRoles(), user.getFullName(), user.getBirthday(),
                user.getEmail(), user.getPhone(), user.getGender());
    }

    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với id: " + userId));
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateRole(Long userId, Set<Role> roles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy người dùng với id: " + userId));
        user.setRoles(roles);
        userRepository.save(user);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
