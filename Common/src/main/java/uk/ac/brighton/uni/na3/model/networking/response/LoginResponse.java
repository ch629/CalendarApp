package uk.ac.brighton.uni.na3.model.networking.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect
public class LoginResponse extends Response {
    private char[] authToken;

    @JsonCreator
    public LoginResponse(ResponseType type, char[] authToken) {
        super(type);
        this.authToken = authToken;
    }

    public char[] getAuthToken() {
        return authToken;
    }
}
