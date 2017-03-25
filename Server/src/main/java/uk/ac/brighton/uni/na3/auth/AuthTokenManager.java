package uk.ac.brighton.uni.na3.auth;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthTokenManager {
    public AuthTokenManager instance = new AuthTokenManager(); //TODO: Maybe put this in the DB & do: "SELECT UserName FROM AuthToken WHERE Token = ?" then check the usernames align.
    private Set<AuthUser> authUsers = new HashSet<>(); //TODO: Timeouts? Require reauth on next request after x time? -> Client would store hashed password to send to the server on reauth requests

    private AuthTokenManager() {}

    public static char[] generateAuthToken() {
        return UUID.randomUUID().toString().toCharArray();
    }

    public char[] generateAndUseAuthToken(String user) { //To be used on login
        authUsers.removeIf(authUser -> authUser.getUserName().equalsIgnoreCase(user)); //Remove already existing auth tokens for that user (When
        AuthUser authUser = new AuthUser(user);
        authUsers.add(authUser);
        return authUser.getToken();
    }

    public char[] getAuthToken(String user) {
        return authUsers.stream()
                .filter(authUser -> authUser.getUserName().equalsIgnoreCase(user))
                .map(AuthUser::getToken).findFirst().orElse(null);
    }

    public String getUser(char[] token) {
        return authUsers.stream()
                .filter(authUser -> authUser.getToken() == token)
                .map(AuthUser::getUserName).findFirst().orElse(null);
    }
}

