package uk.ac.brighton.uni.na3.model.networking.request.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.model.networking.request.Request;

import java.util.Set;

@JsonAutoDetect
public class EventCreateRequest extends Request { //TODO: New SimpleDateTime for this.
    private final String owner, description, location;
    private final SimpleDateTime start, end;
    private final boolean isPrivate;
    private final Set<String> attendees;

    @JsonCreator
    public EventCreateRequest(char[] authToken, String owner, String description, String location,
                              SimpleDateTime start, SimpleDateTime end, boolean isPrivate, Set<String> attendees) {
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

    public SimpleDateTime getStart() {
        return start;
    }

    public SimpleDateTime getEnd() {
        return end;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public Set<String> getAttendees() {
        return attendees;
    }
}