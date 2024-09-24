package quochung.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import quochung.server.model.Role;
import quochung.server.model.User;
import quochung.server.payload.auth.SignInDto;
import quochung.server.payload.auth.SignUpDto;
import quochung.server.repository.UserRepository;
import quochung.server.util.JwtUtils;
import quochung.server.repository.RoleRepository;
import quochung.server.model.RoleName;

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
            throw new UsernameNotFoundException("Username is already taken");
        }

        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpRequest.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        user.getRoles().add(userRole);

        userRepository.save(user);
    }

    public String signIn(SignInDto signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return jwtUtils.generateToken((UserDetailsImplement) authentication.getPrincipal());
    }
}
