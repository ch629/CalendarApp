package uk.ac.brighton.uni.na3.auth;

import java.util.Arrays;

public class AuthUser {
    private final String userName;
    private final boolean admin;
    private final char[] token;

    public AuthUser(String userName, boolean admin, char[] token) {
        this.userName = userName;
        this.admin = admin;
        this.token = token;
    }

    public AuthUser(String userName, char[] token) {
        this(userName, false, token);
    }

    public AuthUser(String userName) {
        this(userName, false, AuthTokenManager.generateAuthToken());
    }

    public AuthUser(String userName, boolean admin) {
        this(userName, admin, AuthTokenManager.generateAuthToken());
    }

    public String getUserName() {
        return userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public char[] getToken() {
        return token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUser authUser = (AuthUser) o;

        return Arrays.equals(token, authUser.token);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(token);
    }
}
