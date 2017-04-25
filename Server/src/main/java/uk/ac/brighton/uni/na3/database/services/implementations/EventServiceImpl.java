package uk.ac.brighton.uni.na3.database.services.implementations;

import org.springframework.stereotype.Service;
import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.repositories.EventRepository;
import uk.ac.brighton.uni.na3.database.services.interfaces.EventService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {
    @Resource
    private EventRepository eventRepository;

    @Override
    @Transactional
    public Event create(Event event) {
        return eventRepository.save(event);
    }

    @Override
    @Transactional
    public Event delete(int id) {
        Event deletedEvent = eventRepository.findOne(id);
        if (deletedEvent == null) return null;
        eventRepository.delete(deletedEvent);
        return deletedEvent;
    }

    @Override
    @Transactional
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Override
    @Transactional
    public Event update(Event event) {
        Event updatedEvent = eventRepository.findOne(event.getEventId());
        if (updatedEvent == null) return null;
        updatedEvent.setAttendees(event.getAttendees());
        updatedEvent.setDescription(event.getDescription());
        updatedEvent.setStartDate(event.getStartDate());
        updatedEvent.setEndDate(event.getEndDate());
        updatedEvent.setLocation(event.getLocation());
        updatedEvent.setOwner(event.getOwner());
        updatedEvent.setPrivate(event.isPrivate());
        return updatedEvent;
    }

    @Override
    @Transactional
    public Event findById(int id) {
        return eventRepository.findOne(id);
    }

    @Override
    @Transactional
    public List<Event> findDatesOverlapping(Timestamp start, Timestamp end) {
        return eventRepository.findEventsOverlapping(start, end);
    }
}