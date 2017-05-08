package uk.ac.brighton.uni.na3.auth;

import uk.ac.brighton.uni.na3.Application;
import uk.ac.brighton.uni.na3.database.entities.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthTokenManager { //TODO: This is kind of like a session in WebDev
    public static final AuthTokenManager instance = new AuthTokenManager(); //TODO: Maybe put this in the DB & do: "SELECT UserName FROM AuthToken WHERE Token = ?" then check the usernames align.
    private final Set<AuthUser> authUsers = new HashSet<>(); //TODO: Timeouts? Require reauth on next request after x time? -> Client would store hashed password to send to the server on reauth requests

    private AuthTokenManager() {
    } //TODO: A temporary solution to deal with too many AuthTokens could be to make a CLI on the server to clear all AuthTokens

    public static char[] generateAuthToken() {
        return UUID.randomUUID().toString().toCharArray();
    }

    public char[] generateAndUseAuthToken(String user) { //To be used on login
        authUsers.removeIf(authUser -> authUser.getUserName().equalsIgnoreCase(user)); //Remove already existing auth tokens for that user
        AuthUser authUser = new AuthUser(user);
        authUsers.add(authUser);
        return authUser.getToken(); //TODO: Actually get user from DB and check admin etc.
    }

    public char[] getAuthToken(String user) {
        return authUsers.stream()
                .filter(authUser -> authUser.getUserName().equalsIgnoreCase(user))
                .map(AuthUser::getToken).findFirst().orElse(null);
    }

    public User getUser(char[] token) {
        return Application.instance.userService.findOne(getUserName(token));
    }

    public String getUserName(char[] token) {
        return authUsers.stream()
                .filter(authUser -> authUser.getToken() == token)
                .map(AuthUser::getUserName).findFirst().orElse(null);
    }
}

