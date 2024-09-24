package quochung.server.payload.user;

import lombok.Data;

@Data
public class PasswordUpdateDto {
    private String newPassword;
}
