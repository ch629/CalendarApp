package uk.ac.brighton.uni.na3.model.networking.request;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.Set;

@JsonAutoDetect
public class EventCreateRequest extends Request {
    private final String owner, description, location;
    private final LocalDateTime start, end;
    private final boolean isPrivate;
    private final Set<String> attendees;

    @JsonCreator
    public EventCreateRequest(char[] authToken, String owner, String description, String location,
                              LocalDateTime start, LocalDateTime end, boolean isPrivate, Set<String> attendees) {
        super(authToken);
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.isPrivate = isPrivate;
        this.attendees = attendees;
    }

    public String getOwner() {
        return owner;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public Set<String> getAttendees() {
        return attendees;
    }
}