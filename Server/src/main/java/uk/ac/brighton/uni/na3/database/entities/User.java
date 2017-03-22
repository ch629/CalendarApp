package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class User {
    @GeneratedValue
    @Id
    private int userId;
    private boolean isAdmin;
    private char[] password;
    private String forename, surname, position, email, phoneNumber;
    @ManyToMany //TODO: Join Table
    private Set<Event> attendingEvents;
}
