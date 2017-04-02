package uk.ac.brighton.uni.na3.model.networking.response;

public class PairDataResponse<T, K> extends Response {
    private final T first;
    private final K second;

    public PairDataResponse(ResponseType type, T first, K second) {
        super(type);
        this.first = first;
        this.second = second;
    }

    public PairDataResponse(ResponseType type) {
        super(type);
        this.first = null;
        this.second = null;
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }
}
