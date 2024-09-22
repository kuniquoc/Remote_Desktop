package quochung.server.service;

import java.util.Set;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import quochung.server.model.User;
import quochung.server.model.Role;
import quochung.server.payload.user.UserProfile;
import quochung.server.payload.user.UserDetailForAdmin;
import quochung.server.payload.user.UserListElement;
import quochung.server.repository.UserRepository;

@Service
public class UserDetailsServiceImplement implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new UserDetailsImplement(user);
    }

    public Long getCurrentUserId() {
        return ((UserDetailsImplement) authentication.getPrincipal()).getId();
    }

    public User getCurrentUser() {
        return userRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + getCurrentUserId()));
    }

    public UserProfile getUserProfile() {
        User user = getCurrentUser();
        return new UserProfile(user.getFullName(), user.getBirthday(), user.getEmail(), user.getPhone(),
                user.getGender());
    }

    public void updateUserProfile(UserProfile userProfile) {
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

    public List<UserListElement> getAllUsers() {
        List<UserListElement> userList = userRepository.findAll().stream()
                .map(user -> new UserListElement(user.getId(), user.getRoles(), user.getFullName(), user.getGender()))
                .toList();
        return userList;
    }

    public UserDetailForAdmin getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
        return new UserDetailForAdmin(user.getId(), user.getRoles(), user.getFullName(), user.getBirthday(),
                user.getEmail(), user.getPhone(), user.getGender());
    }

    public void updatePassword(Long userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
        user.setPassword(new BCryptPasswordEncoder().encode(newPassword));
        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void updateRole(Long userId, Set<Role> roles) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with id: " + userId));
        user.setRoles(roles);
        userRepository.save(user);
    }
}
