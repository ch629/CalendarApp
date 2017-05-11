package uk.ac.brighton.uni.na3.screens;

import javafx.beans.property.SimpleStringProperty;
import uk.ac.brighton.uni.na3.model.Event;

import java.time.LocalTime;

public class EventData {

    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty time = new SimpleStringProperty("");
    private final SimpleStringProperty desc = new SimpleStringProperty("");
    private final SimpleStringProperty duration = new SimpleStringProperty("");
    private final SimpleStringProperty location = new SimpleStringProperty("");

    public EventData(Event event) { //TODO: Update Event type to be LocalTime or easily converted. add name
        this(event.getDescription(), LocalTime.now(), event.getDescription(), event.getDescription(), event.getLocation());
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
    }

    public String getTime() {
        return time.get();
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getDesc() {
        return desc.get();
    }

    public void setDesc(String desc) {
        this.desc.set(desc);
    }

    public String getDuration() {
        return duration.get();
    }

    public void setDuration(String duration) {
        this.duration.set(duration);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

}
