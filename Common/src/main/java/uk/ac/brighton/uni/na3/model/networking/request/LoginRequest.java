package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect
public class LoginRequest extends Request {
    private final String username;
    private final char[] password;

    @JsonCreator
    public LoginRequest(String username, char[] password) {
        super(new char[]{}); //No AuthToken so just blank
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }
}
