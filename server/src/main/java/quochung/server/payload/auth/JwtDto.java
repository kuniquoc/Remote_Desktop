package quochung.server.payload.auth;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDto {
    private String token;
}
