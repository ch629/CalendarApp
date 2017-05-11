package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

@JsonAutoDetect
public abstract class Request {
    private final char[] authToken;

    @JsonCreator
    public Request(char[] authToken) {
        this.authToken = authToken;
    }

    public char[] getAuthToken() {
        return authToken;
    }
}
