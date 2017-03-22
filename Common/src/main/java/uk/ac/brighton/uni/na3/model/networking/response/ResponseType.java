package uk.ac.brighton.uni.na3.model.networking.response;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ResponseType {
    OK(200),
    BAD_REQUEST(400), //Invalid parameters
    UNAUTHORIZED(401),
    NOT_FOUND(404),
    REQUEST_TIMEOUT(408),
    INTERNAL_SERVER_ERROR(500),
    NOT_IMPLEMENTED(501);

    private int code;

    ResponseType(int code) {
        this.code = code;
    }

    @JsonValue
    public int getCode() {
        return code;
    }
}
