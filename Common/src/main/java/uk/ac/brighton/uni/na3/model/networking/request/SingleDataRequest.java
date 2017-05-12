package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class SingleDataRequest<T> extends Request {
    private T data;

    @JsonCreator
    public SingleDataRequest(@JsonProperty("authToken") char[] authToken,
                             @JsonProperty("data") T data) {
        super(authToken);
        this.data = data;
    }

    public SingleDataRequest() {
        super(new char[]{});
    }

    public T getData() {
        return data;
    }
}
