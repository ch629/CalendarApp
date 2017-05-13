package uk.ac.brighton.uni.na3.database.services.interfaces;

import org.springframework.stereotype.Service;
import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface EventService {
    Event create(Event event);

    Event delete(int id);

    List<Event> findAll();

    Event update(Event event);

    Event findById(int id);

    List<Event> findDatesOverlapping(Timestamp start, Timestamp end, UserAccount user);

    List<Event> findDatesOverlapping(Timestamp start, Timestamp end, String user);

    List<Event> findDatesOnDay(Timestamp day, UserAccount user);

    List<Event> findDatesOnDay(Timestamp day, String user);
}
