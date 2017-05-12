package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect
public class PairDataRequest<T, K> extends Request { //TODO: Not sure whether it is worth having this or just using a Pair<X, Y> within the SingleDataRequest
    private final T first;
    private final K second;

    @JsonCreator
    public PairDataRequest(@JsonProperty("authToken") char[] authToken,
                           @JsonProperty("first") T first,
                           @JsonProperty("second") K second) {
        super(authToken);
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }
}
