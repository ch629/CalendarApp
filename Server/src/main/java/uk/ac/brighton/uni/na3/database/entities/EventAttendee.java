package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class EventAttendee implements Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    @Id
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @Id
    @JoinColumn(name = "eventId")
    private Event event;

    public EventAttendee(User user, Event event) {
        this.user = user;
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }
}