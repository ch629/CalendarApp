package uk.ac.brighton.uni.na3.database.services.interfaces;

import uk.ac.brighton.uni.na3.database.entities.Event;

import java.util.List;

public interface EventService {
    Event create(Event event);

    Event delete(int id);

    List<Event> findAll();

    Event update(Event event);

    Event findById(int id);
}
