package quochung.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import quochung.server.payload.MessageDto;
import quochung.server.payload.auth.*;
import quochung.server.service.AuthService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody SignUpDto signUpRequest) {
        try {
            authService.signUp(signUpRequest);
            return ResponseEntity.status(201).body(new MessageDto("User registered successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(409).body(new MessageDto("Username already exists"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody SignInDto signInRequest) {
        try {
            String token = authService.signIn(signInRequest);
            return ResponseEntity.ok(new MessageDto("Login Successful", new JwtDto(token)));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }
}
