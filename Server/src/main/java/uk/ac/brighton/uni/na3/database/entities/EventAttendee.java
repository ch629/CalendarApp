package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class EventAttendee implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private EventAttendeePK id;

    //    @Id
    @ManyToOne
    @JoinColumn(name = "username", insertable = false, updatable = false)
    private UserAccount user;

    //    @Id
    @ManyToOne
    @JoinColumn(name = "eventId", insertable = false, updatable = false)
    private Event event;

    public EventAttendee(UserAccount user, Event event) {
        this.user = user;
        this.event = event;
    }

    public UserAccount getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }
}