package uk.ac.brighton.uni.na3.auth;

import uk.ac.brighton.uni.na3.Application;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class AuthTokenManager {
    public static final AuthTokenManager instance = new AuthTokenManager();
    private final Set<AuthUser> authUsers = new HashSet<>();
    private UserService userService = Application.instance.userService;

    private AuthTokenManager() {
    }

    public static char[] generateAuthToken() {
        return UUID.randomUUID().toString().toCharArray();
    }

    public char[] generateAndUseAuthToken(String user) { //To be used on login
        authUsers.removeIf(authUser -> authUser.getUserName().equalsIgnoreCase(user)); //Remove already existing auth tokens for that user
        UserAccount userAccount = userService.findOne(user);
        if (userAccount != null) {
            AuthUser authUser = new AuthUser(user, userAccount.isAdmin());
            authUsers.add(authUser);
            return authUser.getToken();
        }
        return null;
    }

    public char[] getAuthToken(String user) {
        return authUsers.stream()
                .filter(authUser -> authUser.getUserName().equalsIgnoreCase(user))
                .map(AuthUser::getToken).findFirst().orElse(null);
    }

    public UserAccount getUser(char[] token) {
        String username = getUserName(token);
        return username != null && !username.isEmpty() ?
                Application.instance.userService.findOne(getUserName(token)) : null;
    }

    public String getUserName(char[] token) {
        return authUsers.stream()
                .filter(authUser -> Arrays.equals(authUser.getToken(), token))
                .map(AuthUser::getUserName).findFirst().orElse(null);
    }
}
