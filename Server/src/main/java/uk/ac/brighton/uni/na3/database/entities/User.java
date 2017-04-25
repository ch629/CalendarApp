package uk.ac.brighton.uni.na3.database.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@JsonAutoDetect
@Entity
public class User {
    @GeneratedValue
    @Id
    private int userId;
    private boolean isAdmin;
    private char[] password;
    private String forename, surname, position, email, phoneNumber;
    @ManyToMany //TODO: Join Table
    private Set<Event> attendingEvents;

    public User() {
        attendingEvents = new HashSet<>();
    }

    @JsonCreator //TODO: Check using @JsonProperty
    public User(char[] password, String forename, String surname, String position, String email, String phoneNumber) {
        this();
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getUserId() {
        return userId;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
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

    public void addAttendingEvent(Event event) {
        attendingEvents.add(event);
    }

    public Set<Event> getAttendingEvents() {
        return attendingEvents;
    }

    public void setAttendingEvents(Set<Event> attendingEvents) { //TODO: Check Use
        this.attendingEvents = attendingEvents;
    }
}
