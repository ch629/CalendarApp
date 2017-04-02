package uk.ac.brighton.uni.na3.model.networking.request;

public class PairDataRequest<T, K> extends Request {
    private final T first;
    private final K second;

    public PairDataRequest(char[] authToken, T first, K second) {
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
