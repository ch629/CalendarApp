package uk.ac.brighton.uni.na3.utils;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.na3.model.networking.request.LoginRequest;
import uk.ac.brighton.uni.na3.model.networking.response.LoginResponse;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

public class AuthUtils {
    private static char[] authToken;

    public static boolean login(String username, char[] password) {
        final boolean[] ret = {false};
        NetworkUtils.get("salt/" + username, SingleDataResponse.class).ifOK(saltRes -> {
            LoginRequest loginRequest =
                    new LoginRequest(username, HashingUtils.saltHash(password, (byte[]) saltRes.getData()));
            NetworkUtils.post("login", loginRequest, LoginResponse.class).ifOK(loginRes -> {
                authToken = loginRes.getAuthToken();
                ret[0] = true;
            });
        });
        return ret[0];
    }

    public static boolean register(String username, char[] password) {
        throw new NotImplementedException(); //TODO
    }

    public static char[] getAuthToken() {
        return authToken;
    }
}
