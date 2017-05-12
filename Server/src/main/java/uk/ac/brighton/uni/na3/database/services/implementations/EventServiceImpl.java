package uk.ac.brighton.uni.na3.database.services.implementations;

import org.springframework.stereotype.Service;
import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;
import uk.ac.brighton.uni.na3.database.repositories.EventRepository;
import uk.ac.brighton.uni.na3.database.services.interfaces.EventService;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<Event> findDatesOverlapping(Timestamp start, Timestamp end, String user) {
        List<Event> events = eventRepository.findEventsByAttendees(user);
        return events.stream()
                .filter(event -> event.getStartDate().before(end) && end.before(event.getEndDate()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Event> findDatesOnDay(Timestamp day, UserAccount user) {
        return findDatesOnDay(day, user.getUsername());
    }

    @Override
    public List<Event> findDatesOnDay(Timestamp day, String user) {
        List<Event> events = eventRepository.findEventsByAttendees(user);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(day.getTime());

        return events.stream().filter(event -> {
            Calendar startCal = Calendar.getInstance(), endCal = Calendar.getInstance();
            startCal.setTimeInMillis(event.getStartDate().getTime());
            endCal.setTimeInMillis(event.getStartDate().getTime());
            return isSameDay(startCal, calendar) || isSameDay(endCal, calendar); //TODO: Check if the start is before and end is after
        }).collect(Collectors.toList());
    }

    private boolean isSameDay(Calendar one, Calendar two) {
        return one.get(Calendar.YEAR) == two.get(Calendar.YEAR) &&
                one.get(Calendar.DAY_OF_YEAR) == two.get(Calendar.DAY_OF_YEAR);
    }

    @Override
    @Transactional
    public List<Event> findDatesOverlapping(Timestamp start, Timestamp end, UserAccount user) {
        return findDatesOverlapping(start, end, user.getUsername());
    }


}