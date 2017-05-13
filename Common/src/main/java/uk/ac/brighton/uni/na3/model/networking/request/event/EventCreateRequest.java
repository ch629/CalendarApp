package uk.ac.brighton.uni.na3.model.networking.request.event;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.model.networking.request.Request;

@JsonAutoDetect
public class EventCreateRequest extends Request { //TODO: New SimpleDateTime for this.
    private final String title, description, location;
    private final SimpleDateTime start, end;
    private final boolean isPrivate;
    private final String[] attendees;

    @JsonCreator
    public EventCreateRequest(@JsonProperty("authToken") char[] authToken,
                              @JsonProperty("title") String title,
                              @JsonProperty("description") String description,
                              @JsonProperty("location") String location,
                              @JsonProperty("start") SimpleDateTime start,
                              @JsonProperty("end") SimpleDateTime end,
                              @JsonProperty("private") boolean isPrivate,
                              @JsonProperty("attendees") String[] attendees) {
        super(authToken);
        this.title = title;
        this.description = description;
        this.location = location;
        this.start = start;
        this.end = end;
        this.isPrivate = isPrivate;
        this.attendees = attendees;
    }

    public String getTitle() {
        return title;
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