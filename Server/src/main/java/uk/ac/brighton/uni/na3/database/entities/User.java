package uk.ac.brighton.uni.na3.database.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@JsonAutoDetect
@Entity
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private String username;
    private boolean isAdmin;
    @JsonIgnore
    private char[] password;
    @JsonIgnore
    private char[] salt; //TODO: Store in constructor
    private String forename, surname, position, email, phoneNumber;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JoinTable(name = "EventAttendee",
//            joinColumns = @JoinColumn(name = "username"),
//            inverseJoinColumns = @JoinColumn(name = "user")) //TODO: Check this is correct
    @JsonIgnore
    private Set<EventAttendee> attendingEvents;

    public User() {
        attendingEvents = new HashSet<>();
    }

    @JsonCreator //TODO: Check using @JsonProperty
    public User(String username, String forename, String surname, String position, String email, String phoneNumber) {
        this();
        this.username = username;
        this.forename = forename;
        this.surname = surname;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
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
        attendingEvents.add(new EventAttendee(this, event));
    }

    public Set<Event> getAttendingEvents() {
        return attendingEvents.stream().map(EventAttendee::getEvent).collect(Collectors.toSet());
    }

    public void setAttendingEvents(Set<Event> attendingEvents) { //TODO: Check Use
        this.attendingEvents = attendingEvents.stream().map(event -> new EventAttendee(this, event)).collect(Collectors.toSet());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
