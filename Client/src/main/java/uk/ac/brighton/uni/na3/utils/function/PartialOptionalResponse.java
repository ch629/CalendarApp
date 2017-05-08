package uk.ac.brighton.uni.na3.utils.function;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;

public class PartialOptionalResponse<T extends Response> {
    private ResponseType responseType;

    public void orElse(NetworkAction<T> res) {
        throw new NotImplementedException();
    }
}
