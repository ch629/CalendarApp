package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username, forename, surname, position, email, phoneNumber;
    private boolean isAdmin;

    @JsonCreator
    public User(@JsonProperty("username") String username,
                @JsonProperty("forename") String forename,
                @JsonProperty("surname") String surname,
                @JsonProperty("position") String position,
                @JsonProperty("email") String email,
                @JsonProperty("phoneNumber") String phoneNumber,
                @JsonProperty("isAdmin") boolean isAdmin) {
        this.username = username;
        this.forename = forename;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
