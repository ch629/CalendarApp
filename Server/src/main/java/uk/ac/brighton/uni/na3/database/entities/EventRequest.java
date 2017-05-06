package uk.ac.brighton.uni.na3.database.entities;

import uk.ac.brighton.uni.na3.model.EventRequestResponseType;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class EventRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToOne
    @Id
    @JoinColumn(name = "eventId")
    private Event event;
    @ManyToOne
    @Id
    @JoinColumn(name = "username")
    private User user;
    private EventRequestResponseType response = EventRequestResponseType.NONE;
}
