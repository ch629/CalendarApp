package uk.ac.brighton.uni.na3.model;

import java.time.LocalDateTime;
import java.util.Set;

public class Event {
    private int eventId;
    private User owner;
    private String description, location;
    private LocalDateTime startTime, endTime;
    private int duration; //Calculated? -> Could calculate the endTime from a duration option when creating events
    private boolean isPrivate;
    private Set<User> attendees;
}
