package uk.ac.brighton.uni.na3.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Deprecated
public class Event {
    private int eventId;
    private User owner;
    private String description, location;
    private LocalDateTime startTime, endTime;
    private boolean isPrivate;
    private Set<User> attendees;

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
