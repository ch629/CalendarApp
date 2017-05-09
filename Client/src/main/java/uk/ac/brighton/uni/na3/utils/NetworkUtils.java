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

    public static <T extends Response> NetworkOptional<T> get(String route, Class<T> type) { //TODO: Don't think java supports generating the T from a method, so may have to include this in Request to make it easier.
        Response response;
        try {
            response = Unirest.get(HOST_NAME + route).asObject(Response.class).getBody();
        } catch (UnirestException ignored) {
            return null; //TODO: Return lambda object with error (ifOK = false)
        }

        throw new NotImplementedException();
    }

    public static NetworkOptional post(String route, Request request) {
        throw new NotImplementedException();
    }
}
