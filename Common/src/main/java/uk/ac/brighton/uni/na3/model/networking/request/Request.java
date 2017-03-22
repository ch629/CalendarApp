package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public abstract class Request {
    private char[] authToken;

    public Request(char[] authToken) {
        this.authToken = authToken;
    }

    public char[] getAuthToken() {
        return authToken;
    }
}
