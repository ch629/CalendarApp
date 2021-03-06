package uk.ac.brighton.uni.na3.database.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import uk.ac.brighton.uni.na3.Application;
import uk.ac.brighton.uni.na3.auth.AuthTokenManager;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.model.User;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@JsonAutoDetect
public class Event implements Serializable {
    private static final long serialVersionUID = 1L;
    @GeneratedValue
    @Id
    private int eventId;
    private String title;
    @ManyToOne
    private UserAccount owner;
    private String description, location;
    private Timestamp startDate, endDate;
    private boolean isPrivate;
    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<EventAttendee> attendees;

    public Event() {}

    public Event(int eventId, UserAccount owner, String title, String description, String location, Timestamp startDate, Timestamp endDate, boolean isPrivate) {
        this(owner, title, description, location, startDate, endDate, isPrivate);
        this.eventId = eventId;
    }

    @JsonCreator
    public Event(UserAccount owner, String title, String description, String location, Timestamp startDate, Timestamp endDate, boolean isPrivate) {
        this.title = title;
        this.owner = owner;
        this.description = description;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isPrivate = isPrivate;
    }

    public static Event fromCommon(uk.ac.brighton.uni.na3.model.Event event) {
        UserAccount owner = Application.instance.userService.findOne(event.getOwner().getUsername());
        if (owner != null) {
            return new Event(event.getEventId(), owner, event.getTitle(), event.getDescription(), event.getLocation(),
                    event.getStartDate().toTimestamp(), event.getEndDate().toTimestamp(), event.isPrivate());
        }
        return null;
    }

    public static Event fromCreateRequest(EventCreateRequest request) {
        UserAccount creator = AuthTokenManager.instance.getUser(request.getAuthToken());
        if (creator == null) return null;
        return new Event(creator, request.getTitle(), request.getDescription(), request.getLocation(),
                request.getStart().toTimestamp(), request.getEnd().toTimestamp(), request.isPrivate());
    }

    public int getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UserAccount getOwner() {
        return owner;
    }

    public void setOwner(UserAccount owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public void addAttendee(UserAccount user) {
        attendees.add(new EventAttendee(user, this));
    }

    public Set<UserAccount> getAttendees() {
        return attendees != null ? attendees.stream().map(EventAttendee::getUser).collect(Collectors.toSet()) : new HashSet<>();
    }

    public void setAttendees(Set<UserAccount> attendees) {
        this.attendees = attendees.stream().map(user -> new EventAttendee(user, this)).collect(Collectors.toSet());
    }

    public uk.ac.brighton.uni.na3.model.Event toCommonEvent() {
        return new uk.ac.brighton.uni.na3.model.Event(eventId, title, owner.toCommonUser(), description,
                location, new SimpleDateTime(startDate), new SimpleDateTime(endDate), isPrivate,
                getAttendeesAsUsers().toArray(new User[0]));
    }

    @JsonIgnore
    private List<User> getAttendeesAsUsers() {
        return attendees != null ?
                attendees.stream()
                        .map(eventAttendee -> eventAttendee.getUser().toCommonUser()).collect(Collectors.toList())
                : new ArrayList<>();
    }
}
