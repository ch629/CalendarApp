package uk.ac.brighton.uni.na3.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uk.ac.brighton.uni.na3.database.entities.Event;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
    List<Event> findEventsByAttendees(String user);
}