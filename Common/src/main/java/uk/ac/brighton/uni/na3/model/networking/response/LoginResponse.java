package uk.ac.brighton.uni.na3.model.networking.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class LoginResponse extends Response {
    private char[] authToken;

    @JsonCreator
    public LoginResponse(@JsonProperty("type") ResponseType type,
                         @JsonProperty("authToken") char[] authToken) {
        super(type);
        this.authToken = authToken;
    }

    public char[] getAuthToken() {
        return authToken;
    }
}
