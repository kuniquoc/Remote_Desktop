package quochung.server.controller;

import java.util.Set;
import quochung.server.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import quochung.server.payload.user.*;
import quochung.server.service.UserDetailsImplement;
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
            return ResponseEntity.ok(userDetailsServiceImplement.getUserProfile());
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Get user profile failed");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/profile")
    public ResponseEntity<?> updateUserProfile(@RequestBody UserProfile userProfile) {
        try {
            userDetailsServiceImplement.updateUserProfile(userProfile);
            return ResponseEntity.ok("User profile updated successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User profile update failed");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/user/password")
    public ResponseEntity<?> updateUserPassword(@RequestBody String newPassword) {
        try {
            userDetailsServiceImplement.updatePassword(newPassword);
            return ResponseEntity.ok("User password updated successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User password update failed");
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user")
    public ResponseEntity<?> deleteUser() {
        try {
            userDetailsServiceImplement.deleteUser();
            return ResponseEntity.ok("User deleted successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User delete failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        try {
            return ResponseEntity.ok(userDetailsServiceImplement.getAllUsers());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Get all users failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userDetailsServiceImplement.getUserById(id));
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("Get user by id failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/password")
    public ResponseEntity<?> updateUserPassword(@PathVariable Long id, @RequestBody String newPassword) {
        try {
            userDetailsServiceImplement.updatePassword(id, newPassword);
            return ResponseEntity.ok("User password updated successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User password update failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userDetailsServiceImplement.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User delete failed");
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/users/{id}/role")
    public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody Set<Role> roles) {
        try {
            userDetailsServiceImplement.updateRole(id, roles);
            return ResponseEntity.ok("User role updated successfully");
        } catch (UsernameNotFoundException e) {
            System.out.println(e.getMessage());
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("User role update failed");
        }
    }

}
