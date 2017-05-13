package uk.ac.brighton.uni.na3.model.networking.request.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.model.networking.request.Request;

@JsonAutoDetect
public class EventCreateRequest extends Request { //TODO: New SimpleDateTime for this.
    private final String description, location;
    private final SimpleDateTime start, end;
    private final boolean isPrivate;
    private final String[] attendees;

    @JsonCreator
    public EventCreateRequest(char[] authToken, String description, String location,
                              SimpleDateTime start, SimpleDateTime end, boolean isPrivate, String[] attendees) {
        super(authToken);
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.isPrivate = isPrivate;
        this.attendees = attendees;
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

    public String[] getAttendees() {
        return attendees;
    }
}