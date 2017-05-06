package uk.ac.brighton.uni.na3.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.na3.database.entities.Event;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    //    @Query("SELECT e FROM Event INNER JOIN EventAttendee ON EventAttendee.event = e.eventId WHERE e.startDate <= ?2 AND ?1 <= e.endDate AND EventAttendee.User = ?3")
    @Query("SELECT e FROM Event INNER JOIN EventAttendee as a WHERE e.startDate <= ?2 AND ?1 <= e.endDate AND a.User = ?3")
    List<Event> findEventsOverlapping(Timestamp start, Timestamp end, String user);
}