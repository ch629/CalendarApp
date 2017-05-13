package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.io.Serializable;
import java.util.Set;

@JsonAutoDetect
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private int eventId;
    private User owner;
    private String description, location;
    private SimpleDateTime startDate, endDate;
    private boolean isPrivate;
    private Set<User> attendees;

    @JsonCreator
    public Event(int eventId, User owner, String description, String location, SimpleDateTime startDate, SimpleDateTime endDate, boolean isPrivate, Set<User> attendees) {
        this.eventId = eventId;
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPrivate = isPrivate;
        this.attendees = attendees;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
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

    public SimpleDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(SimpleDateTime startDate) {
        this.startDate = startDate;
    }

    public SimpleDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(SimpleDateTime endDate) {
        this.endDate = endDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Set<User> getAttendees() {
        return attendees;
    }

    public void setAttendees(Set<User> attendees) {
        this.attendees = attendees;
    }
}
