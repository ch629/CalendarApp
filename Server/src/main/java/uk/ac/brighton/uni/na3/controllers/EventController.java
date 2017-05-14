package uk.ac.brighton.uni.na3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.brighton.uni.na3.Application;
import uk.ac.brighton.uni.na3.auth.AuthTokenManager;
import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.entities.UserAccount;
import uk.ac.brighton.uni.na3.database.services.interfaces.EventService;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;
import uk.ac.brighton.uni.na3.model.SimpleDate;
import uk.ac.brighton.uni.na3.model.networking.request.PairDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/event/")
@EnableAutoConfiguration
public class EventController {
    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @PostMapping("event/create")
    @ResponseBody
    Response createEvent(@RequestBody EventCreateRequest request) {
        Event newEvent = Event.fromCreateRequest(request);
        if (newEvent == null)
            return new Response(ResponseType.BAD_REQUEST); //TODO: Maybe INVALID_PARAMETERS ResponseType
        newEvent = eventService.create(newEvent); //TODO: Send invites to the set attendees
        return new SingleDataResponse<>(newEvent.getEventId());
    }

    @PostMapping("event/get")
    @ResponseBody
    @Deprecated
        //TODO: Remove?
    Response getEvent(@RequestBody SingleDataRequest<Integer> request) { //NOTE: This route probably wont be used and could be used to get events from another user.
        Event event = eventService.findById(request.getData());
        if (event == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(event.toCommonEvent());
    }

    @GetMapping("event/between")
    @ResponseBody
    Response getEventsBetween(PairDataRequest<Long, Long> request) { //TODO: Long could probably just be the dates, as Jackson serializes these to longs by default
        UserAccount user = AuthTokenManager.instance.getUser(request.getAuthToken());
        Timestamp start = new Timestamp(request.getFirst()), end = new Timestamp(request.getSecond());
        List<Event> events = eventService.findDatesOverlapping(start, end, user.getUsername());
        if (events == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(toCommonEvent(events));
    }

    @PostMapping("event/day")
    @ResponseBody
    Response getEventsOnDay(@RequestBody SingleDataRequest<SimpleDate> request) {
        UserAccount user = AuthTokenManager.instance.getUser(request.getAuthToken());
        Timestamp day = request.getData().toTimestamp();
        List<Event> events = eventService.findDatesOnDay(day, user);
        if (events == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(toCommonEvent(events));
    }

    private List<uk.ac.brighton.uni.na3.model.Event> toCommonEvent(List<Event> from) {
        return from.stream().map(Event::toCommonEvent).collect(Collectors.toList());
    }

    @PostMapping("event/invite")
    @ResponseBody
    Response inviteToEvent(@RequestBody PairDataRequest<Integer, String> request) { //NOTE: Only the owner can invite
        UserAccount inviter = AuthTokenManager.instance.getUser(request.getAuthToken()); //TODO: IMPLEMENT
        Event event = eventService.findById(request.getFirst());
        if (event.getOwner().getUsername().equals(inviter.getUsername()) &&
                !inviter.getUsername().equals(request.getSecond())) {
            UserAccount invitee = userService.findOne(request.getSecond());
            if (invitee != null) {
                event.addAttendee(invitee);
                eventService.update(event);
                return new Response(ResponseType.OK);
            }
        }
        return new Response(ResponseType.BAD_REQUEST); //TODO: Fix the error requests for all of the routes.
    }

    @PostMapping("event/edit")
    @ResponseBody
    Response editEvent(@RequestBody SingleDataRequest<uk.ac.brighton.uni.na3.model.Event> request) {
        UserAccount user = AuthTokenManager.instance.getUser(request.getAuthToken());
        Event event = Event.fromCommon(request.getData());
        if (event != null && user != null) {
            //Check user is attending or owner
            if (event.getOwner().getUsername().equals(user.getUsername())
                    || event.getAttendees().stream()
                    .anyMatch(userAccount -> userAccount.getUsername().equals(user.getUsername()))) {
                Event oldEvent = Application.instance.eventService.findById(event.getEventId());
                if (oldEvent != null) {
                    oldEvent.setTitle(event.getTitle());
                    oldEvent.setAttendees(event.getAttendees());
                    oldEvent.setDescription(event.getDescription());
                    oldEvent.setStartDate(event.getStartDate());
                    oldEvent.setEndDate(event.getEndDate());
                    oldEvent.setPrivate(event.isPrivate());

                    Application.instance.eventService.update(oldEvent); //Update in the DB
                    return new Response(ResponseType.OK);
                }
            }
        }
        return new Response(ResponseType.BAD_REQUEST);
    }

    @PostMapping("event/delete")
    @ResponseBody
    Response deleteEvent(@RequestBody SingleDataRequest<Integer> request) {
        UserAccount userAccount = AuthTokenManager.instance.getUser(request.getAuthToken());
        Event event = Application.instance.eventService.findById(request.getData());
        if (event != null && event.getOwner().getUsername().equals(userAccount.getUsername())) {
            Application.instance.eventService.delete(request.getData());
            return new Response(ResponseType.OK);
        }
        return new Response(ResponseType.BAD_REQUEST);
    }
}