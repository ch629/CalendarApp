package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonAutoDetect
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    private int eventId;
    private User owner;
    private String title, description, location;
    private SimpleDateTime startDate, endDate;
    private boolean isPrivate;
    private User[] attendees;

    @JsonCreator
    public Event(@JsonProperty("eventId") int eventId,
                 @JsonProperty("title") String title,
                 @JsonProperty("owner") User owner,
                 @JsonProperty("description") String description,
                 @JsonProperty("location") String location,
                 @JsonProperty("startDate") SimpleDateTime startDate,
                 @JsonProperty("endDate") SimpleDateTime endDate,
                 @JsonProperty("isPrivate") boolean isPrivate,
                 @JsonProperty("attendees") User[] attendees) {
        this.eventId = eventId;
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPrivate = isPrivate;
        this.attendees = attendees;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public User[] getAttendees() {
        return attendees;
    }

    public void setAttendees(User[] attendees) {
        this.attendees = attendees;
    }
}
