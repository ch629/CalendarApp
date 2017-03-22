package uk.ac.brighton.uni.na3.model;

import java.time.LocalDateTime;

public class EventRequest { //TODO: These models probably wont be used directly, more likely to send a Packet containing -> This just represents what the Tables should look like.
    private Event event;
    private User sender, receiver;
    private LocalDateTime invited;
    private boolean seen;
    private EventRequestResponseType response = EventRequestResponseType.NONE;
}
