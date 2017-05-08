package uk.ac.brighton.uni.na3.utils.function;

import uk.ac.brighton.uni.na3.model.networking.response.Response;

@FunctionalInterface
public interface NetworkAction<T extends Response> {
    void run(T res);
}
