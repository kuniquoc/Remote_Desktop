package quochung.server.controller;

import java.util.Set;
import quochung.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import quochung.server.payload.MessageDto;
import quochung.server.payload.user.*;
import quochung.server.service.UserDetailsServiceImplement;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserDetailsServiceImplement userDetailsServiceImplement;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<?> getUserProfile() {
        try {
            return ResponseEntity.ok(new MessageDto("User information retrieved successfully",
                    userDetailsServiceImplement.getUserProfile()));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileDto userProfile) {
        try {
            userDetailsServiceImplement.updateUserProfile(userProfile);
            return ResponseEntity.ok(new MessageDto("User profile updated successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody String newPassword) {
        try {
            userDetailsServiceImplement.updatePassword(newPassword);
            return ResponseEntity.ok(new MessageDto("User password updated successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser() {
        try {
            userDetailsServiceImplement.deleteUser();
            return ResponseEntity.noContent().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("User delete failed"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(
                    new MessageDto("User list retrieved successfully", userDetailsServiceImplement.getAllUsers()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new MessageDto("User information retrieved successfully",
                    userDetailsServiceImplement.getUserById(id)));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Get user by id failed"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        try {
            userDetailsServiceImplement.updatePassword(id, newPassword);
            return ResponseEntity.ok(new MessageDto("User password updated successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userDetailsServiceImplement.deleteUser(id);
            return ResponseEntity.noContent().build();
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("users/roles")
    public ResponseEntity<?> getAllRoles() {
        try {
            return ResponseEntity
                    .ok(new MessageDto("Roles retrieved successfully", userDetailsServiceImplement.getAllRoles()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody Set<Role> roles) {
        try {
            userDetailsServiceImplement.updateRole(id, roles);
            return ResponseEntity.ok(new MessageDto("User role updated successfully"));
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageDto("User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageDto("Internal Server Error"));
        }
    }

}
