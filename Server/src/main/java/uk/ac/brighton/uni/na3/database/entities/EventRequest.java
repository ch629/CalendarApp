package uk.ac.brighton.uni.na3.database.entities;

import uk.ac.brighton.uni.na3.model.EventRequestResponseType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class EventRequest {
    @Id
    private int requestId; //TODO: This is temp, composite ID of EventID && Username are required
    @ManyToOne
    private Event event;
    private EventRequestResponseType response = EventRequestResponseType.NONE;
    private boolean seen = false;
    private LocalDateTime invitedDate;
}
