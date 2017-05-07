package uk.ac.brighton.uni.na3.model.networking.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class SingleDataResponse<T> extends Response {
    private final T data;

    public SingleDataResponse(ResponseType type, T data) {
        super(type);
        this.data = data;
    }

    public SingleDataResponse(ResponseType type) {
        super(type);
        this.data = null;
    }

    public SingleDataResponse(T data) {
        this(ResponseType.OK, data);
    }

    public T getData() {
        return data;
    }
}
