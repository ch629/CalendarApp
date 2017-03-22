package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Event {
    @GeneratedValue
    @Id
    private int eventId;
    @OneToOne
    private User owner;
    private String description, location;
    private LocalDateTime startDate, endDate;
    private boolean isPrivate;
    @ManyToMany //TODO: Join table of Attendees
    private Set<User> attendees;
}
