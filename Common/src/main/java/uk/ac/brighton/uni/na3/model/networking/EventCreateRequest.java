package uk.ac.brighton.uni.na3.model.networking;

import java.time.LocalDateTime;
import java.util.Set;

public class EventCreateRequest extends Request {
    private String owner; //ID
    private String description, location;
    private LocalDateTime start, end;
    private boolean isPrivate;
    private Set<String> attendees;
}