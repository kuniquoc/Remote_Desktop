package quochung.server.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import quochung.server.model.User;

@AllArgsConstructor
public class UserDetailsImplement implements UserDetails {
    private Long id;
    private String username;
    //private String email;
    private String password;

    public static UserDetailsImplement build(User user) {
        return new UserDetailsImplement(
                user.getId(),
                user.getUsername(),
                //user.getEmail(),
                user.getPassword());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }

    // public String getEmail() {
    //     return email;
    // }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
