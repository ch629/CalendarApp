package uk.ac.brighton.uni.na3.utils.function;

import uk.ac.brighton.uni.na3.model.networking.response.Response;

import java.util.function.Predicate;

public class PartialOptionalResponse<T extends Response> {
    private final Predicate<Response> predicate;
    private T response;

    public PartialOptionalResponse(Predicate<Response> previousPredicate, T response) {
        this.response = response;
        this.predicate = previousPredicate.negate();
    }

    public void orElse(NetworkAction<T> res) {
        if (predicate.test(response)) res.run(response);
    }
}
