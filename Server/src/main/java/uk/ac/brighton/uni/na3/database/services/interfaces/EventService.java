package uk.ac.brighton.uni.na3.database.services.interfaces;

import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.entities.User;

import java.sql.Timestamp;
import java.util.List;

public interface EventService {
    Event create(Event event);

    Event delete(int id);

    List<Event> findAll();

    Event update(Event event);

    Event findById(int id);

    List<Event> findDatesOverlapping(Timestamp start, Timestamp end, User user);

    List<Event> findDatesOverlapping(Timestamp start, Timestamp end, String user);

    List<Event> findDatesOnDay(Timestamp day, User user);

    List<Event> findDatesOnDay(Timestamp day, String user);
}
