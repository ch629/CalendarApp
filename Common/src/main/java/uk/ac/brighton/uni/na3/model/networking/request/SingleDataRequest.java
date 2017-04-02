package uk.ac.brighton.uni.na3.model.networking.request;

public class SingleDataRequest<T> extends Request {
    private final T data;

    public SingleDataRequest(char[] authToken, T data) {
        super(authToken);
        this.data = data;
    }

    public T getData() {
        return data;
    }
}
