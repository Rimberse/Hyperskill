package cinema.model.DTOs;

import java.util.UUID;

public class TokenDTO {
    private final String token;

    public TokenDTO() {
        this.token = UUID.randomUUID().toString();
    }

    public String getToken() {
        return token;
    }
}
