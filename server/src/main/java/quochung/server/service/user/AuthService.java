package quochung.server.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import quochung.server.payload.auth.JwtDto;
import quochung.server.payload.auth.SignInDto;
import quochung.server.payload.auth.SignUpDto;
import quochung.server.util.JwtUtils;
import quochung.server.repository.user.RoleRepository;
import quochung.server.repository.user.UserRepository;
import quochung.server.model.user.Role;
import quochung.server.model.user.RoleName;
import quochung.server.model.user.User;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public void signUp(SignUpDto signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new UsernameNotFoundException("Tên người dùng đã tồn tại");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        user.getRoles().add(userRole);

        userRepository.save(user);
    }

    public JwtDto signIn(SignInDto signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return new JwtDto(jwtUtils.generateToken((UserDetailsImplement) authentication.getPrincipal()),
                ((UserDetailsImplement) authentication.getPrincipal()).getAuthorities());
    }
}
