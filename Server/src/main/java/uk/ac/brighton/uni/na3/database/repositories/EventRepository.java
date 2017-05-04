package uk.ac.brighton.uni.na3.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.na3.database.entities.Event;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    @Query("SELECT e FROM Event INNER JOIN EventAttendee ON EventAttendee.event = Event.eventId WHERE Event.startDate <= ?2 AND ?1 <= Event.endDate AND EventAttendee.User = ?3")
    List<Event> findEventsOverlapping(Timestamp start, Timestamp end, String user);
}