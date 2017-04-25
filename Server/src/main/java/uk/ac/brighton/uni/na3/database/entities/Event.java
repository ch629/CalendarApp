package uk.ac.brighton.uni.na3.database.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.ac.brighton.uni.na3.Application;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@JsonAutoDetect
public class Event {
    @GeneratedValue
    @Id
    private int eventId;
    @OneToOne
    private User owner;
    private String description, location;
    private Timestamp startDate, endDate; //TODO: These may have to use the SQL Timestamp class
    private boolean isPrivate;
    @ManyToMany //TODO: Join table of Attendees
    @JsonIgnore
    private Set<User> attendees;

    @JsonCreator
    public Event(User owner, String description, String location, Timestamp startDate, Timestamp endDate, boolean isPrivate) {
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPrivate = isPrivate;
    }

    public static Event fromCreateRequest(EventCreateRequest request) {
        User owner = Application.instance.userService.findOne(request.getOwner());
        if (owner == null) return null;
        return new Event(owner, request.getDescription(), request.getLocation(),
                request.getStart(), request.getEnd(), request.isPrivate());
    }

    public int getEventId() {
        return eventId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void addAttendee(User user) {
        attendees.add(user);
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }
}
