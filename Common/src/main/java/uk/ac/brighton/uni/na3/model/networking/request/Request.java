package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.ac.brighton.uni.na3.model.networking.response.Response;

@JsonAutoDetect
public abstract class Request<T extends Response> {
    private final char[] authToken;
    @JsonIgnore
    private T expectedResponse; //Can't be final as we don't want this in the JSON

    @JsonCreator
    public Request(char[] authToken) {
        this.authToken = authToken;
    }

    public Request(char[] authToken, T expectedResponse) {
        this(authToken);
        this.expectedResponse = expectedResponse;
    }

    public char[] getAuthToken() {
        return authToken;
    }

    public T getExpectedResponse() {
        return expectedResponse;
    }

    public T castResponse(Response response) {
        return (T) response;
    }
}
