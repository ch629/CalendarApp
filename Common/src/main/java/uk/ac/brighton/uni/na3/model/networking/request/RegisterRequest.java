package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class RegisterRequest { //TODO: Add other fields.
    private String username;
    private char[] password;
    private byte[] salt;

    @JsonCreator
    public RegisterRequest(@JsonProperty("username") String username,
                           @JsonProperty("password") char[] password,
                           @JsonProperty("salt") byte[] salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    public String getUsername() {
        return username;
    }

    public char[] getPassword() {
        return password;
    }

    public byte[] getSalt() {
        return salt;
    }
}
