package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class EventAttendee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "username")
    private UserAccount user;

    @Id
    @ManyToOne
    @JoinColumn(name = "eventId")
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