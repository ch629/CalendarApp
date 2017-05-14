package uk.ac.brighton.uni.na3.screens;

import javafx.beans.property.SimpleStringProperty;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class EventData {
    private Event event = null;

    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty desc = new SimpleStringProperty("");
    private final SimpleStringProperty duration = new SimpleStringProperty("");
    private final SimpleStringProperty location = new SimpleStringProperty("");

    public EventData(Event event) {
        this(event.getTitle(), event.getStartDate(), event.getEndDate(), event.getDescription(), event.getLocation());
        this.event = event;
    }

    public EventData(String name, SimpleDateTime start, SimpleDateTime end, String desc, String location) {
        this(name, start.toLocalTime(), desc,
                String.valueOf(start.toLocalTime().until(end.toLocalTime(), ChronoUnit.MINUTES)), location);
    }

    public EventData(String name, LocalTime time, String desc, String duration, String location) {
        setName(name);
        setTime(time.toString());
        setDesc(desc);
        setDuration(duration);
        setLocation(location);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
        event.setTitle(name);
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
        event.setStartDate(new SimpleDateTime(LocalDateTime.parse(time))); //TODO: CHECK?
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
        event.setDescription(desc);
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
        event.setEndDate(new SimpleDateTime(
                event.getStartDate().toLocalDateTime().plus(Integer.parseInt(duration), ChronoUnit.MINUTES)));
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
        event.setLocation(location);
    }

    public Event toCommonEvent() {
        return event;
    }
}
