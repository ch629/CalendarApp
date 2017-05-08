package uk.ac.brighton.uni.na3.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.na3.model.networking.request.Request;
import uk.ac.brighton.uni.na3.model.networking.response.Response;

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
}
