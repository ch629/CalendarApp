package uk.ac.brighton.uni.na3.utils.function;

import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;

import java.util.function.Predicate;

public class NetworkOptional<T extends Response> {
    private static Predicate<Response> isOK;
    private T response;

    public NetworkOptional(T response) {
        this.response = response;
        if (response != null) isOK = res -> res.getType() == ResponseType.OK;
        else setInvalid();
    }

    public NetworkOptional() {
        setInvalid();
    }

    private void setInvalid() {
        isOK = res -> false;
    }

    public PartialOptionalResponse ifOK(NetworkAction<T> res) {
        if (isOK.test(response)) res.run(response);

        return new PartialOptionalResponse<>(isOK, response);
    }
}