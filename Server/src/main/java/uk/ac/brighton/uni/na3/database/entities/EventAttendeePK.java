package uk.ac.brighton.uni.na3.database.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class EventAttendeePK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(insertable = false, updatable = false)
    private String username;

    @Column(insertable = false, updatable = false)
    private int eventId;
}
