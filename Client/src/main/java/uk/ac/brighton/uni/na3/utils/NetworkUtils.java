package uk.ac.brighton.uni.na3.utils;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.utils.function.NetworkOptional;

public class NetworkUtils {
    private static final String HOST_NAME = "http://localhost:3000/";
    public static <T extends Response> NetworkOptional<T> get(String route, Class<T> type) {
        try {
            T response = Unirest.get(HOST_NAME + route).asObject(type).getBody();
            return new NetworkOptional<>(response);
        } catch (UnirestException ignored) {
        }
        return new NetworkOptional<>();
    }

    public static <T extends Response> NetworkOptional<T> post(String route, Object request, Class<T> type) {
        try {
            T response = Unirest.post(HOST_NAME + route).header("Content-Type", "application/json").body(request).asObject(type).getBody();
            return new NetworkOptional<>(response);
        } catch (UnirestException ignored) {
        }
        return new NetworkOptional<>();
    }
}
