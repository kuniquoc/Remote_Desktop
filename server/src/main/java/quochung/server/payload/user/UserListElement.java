package quochung.server.payload.user;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import quochung.server.model.Role;

@Data
@AllArgsConstructor
public class UserListElement {
    private Long id;
    private Set<Role> roles;
    private String fullName;
    private String gender;
}
