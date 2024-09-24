package quochung.server.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpDto {
    private String username;
    private String password;
}
