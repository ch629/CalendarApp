package uk.ac.brighton.uni.na3.utils;

import com.mashape.unirest.http.exceptions.UnirestException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import uk.ac.brighton.uni.na3.model.networking.request.LoginRequest;
import uk.ac.brighton.uni.na3.model.networking.response.LoginResponse;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

public class AuthUtils {
    private static char[] authToken;

    public static boolean login(String username, char[] password) throws UnirestException {
        Response response = NetworkUtils.getRequest("salt/%s", username);
        if (response.getType() == ResponseType.OK) {
            SingleDataResponse<byte[]> saltResponse = (SingleDataResponse<byte[]>) response;
            byte[] salt = saltResponse.getData();
            char[] saltPass = HashingUtils.saltHash(password, salt);

            LoginRequest loginRequest = new LoginRequest(username, saltPass);
            Response loginResponse = NetworkUtils.postRequest("login", loginRequest);
            if (loginResponse.getType() == ResponseType.OK) {
                LoginResponse castedResponse = (LoginResponse) loginResponse;
                authToken = castedResponse.getAuthToken();
                return true;
            }
        }
        return false;
    }

    public static boolean register(String username, char[] password) {
        throw new NotImplementedException(); //TODO
    }

    public static char[] getAuthToken() {
        return authToken;
    }
}
