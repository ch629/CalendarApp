package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect
public class RegisterRequest extends Request { //TODO: Add other fields.
    private String username;
    private char[] password;
    private byte[] salt;

    @JsonCreator
    public RegisterRequest(String username, char[] password, byte[] salt) {
        super(new char[]{});
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
