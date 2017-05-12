package uk.ac.brighton.uni.na3.utils;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.na3.model.networking.request.Request;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.utils.function.NetworkOptional;

public class NetworkUtils {
    //TODO: Try and make these methods more generic, allow auto casting to a type if OK, could do some lambda stuff here.
    //TODO: Perhaps within a Request it holds it's expected Response class. -> Can do some stuff like getRequest("route").ifOK(Function)
    private static final String HOST_NAME = "http://localhost:8080/";

    public static Response getRequest(String route) {
        throw new NotImplementedException();
    }

    public static Response getRequest(String route, Object... fmt) {
        return getRequest(String.format(route, fmt));
    }

    public static Response postRequest(String route, Request request) {
        throw new NotImplementedException();
    }

    public static <T extends Response> NetworkOptional<T> get(String route, Class<T> type) {
        try {
            Response response = Unirest.get(HOST_NAME + route).asObject(Response.class).getBody();
            return new NetworkOptional<>((T) response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new NetworkOptional<>();
    }

    public static <T extends Response> NetworkOptional<T> get(String route, Class<T> type, Object... fmt) {
        return get(String.format(route, fmt), type);
    }

    public static <T extends Response> NetworkOptional<T> post(String route, Object request, Class<T> type) {
        try {
            Response response = Unirest.post(HOST_NAME + route).body(request).asObject(Response.class).getBody();
            return new NetworkOptional<>((T) response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return new NetworkOptional<>();
    }

    public static <T extends Response> NetworkOptional<T> post(String route, Object request, Class<T> type, Object... fmt) {
        return post(String.format(route, fmt), request, type);
    }
}
