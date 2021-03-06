package uk.ac.brighton.uni.na3.model.networking.response;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
public class Response {
    private ResponseType type;

    public Response() {}

    public Response(ResponseType type) {
        this.type = type;
    }

    public ResponseType getType() {
        return type;
    }
}
