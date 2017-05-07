package uk.ac.brighton.uni.na3.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.brighton.uni.na3.auth.AuthTokenManager;
import uk.ac.brighton.uni.na3.database.entities.Event;
import uk.ac.brighton.uni.na3.database.entities.User;
import uk.ac.brighton.uni.na3.database.services.interfaces.EventService;
import uk.ac.brighton.uni.na3.database.services.interfaces.UserService;
import uk.ac.brighton.uni.na3.model.networking.request.PairDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.sql.Timestamp;
import java.util.List;

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

    @PostMapping("/create")
    @ResponseBody
    Response createEvent(EventCreateRequest request) {
        Event newEvent = Event.fromCreateRequest(request);
        if (newEvent == null)
            return new Response(ResponseType.BAD_REQUEST); //TODO: Maybe INVALID_PARAMETERS ResponseType
        newEvent = eventService.create(newEvent); //TODO: Send invites to the set attendees
        return new SingleDataResponse<>(newEvent.getEventId());
    }

    @PostMapping("/get")
    @ResponseBody
    @Deprecated
        //TODO: Remove?
    Response getEvent(SingleDataRequest<Integer> request) { //NOTE: This route probably wont be used and could be used to get events from another user.
        Event event = eventService.findById(request.getData());
        if (event == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(event);
    }

    @GetMapping("/between")
    @ResponseBody
    Response getEventsBetween(PairDataRequest<Long, Long> request) { //TODO: Long could probably just be the dates, as Jackson serializes these to longs by default
        User user = AuthTokenManager.instance.getUser(request.getAuthToken());
        Timestamp start = new Timestamp(request.getFirst()), end = new Timestamp(request.getSecond());
        List<Event> events = eventService.findDatesOverlapping(start, end, user.getUsername());
        if (events == null) return new Response(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(events);
    }

    @PostMapping("/invite")
    @ResponseBody
    Response inviteToEvent(PairDataRequest<Integer, String> request) { //NOTE: Only the owner can invite
        User inviter = AuthTokenManager.instance.getUser(request.getAuthToken()); //TODO: IMPLEMENT
        Event event = eventService.findById(request.getFirst());
        if (event.getOwner().getUsername().equals(inviter.getUsername()) &&
                !inviter.getUsername().equals(request.getSecond())) {
            User invitee = userService.findOne(request.getSecond());
            if (invitee != null) {
                event.addAttendee(invitee);
                eventService.update(event);
                return new Response(ResponseType.OK);
            }
        }
        return new Response(ResponseType.BAD_REQUEST); //TODO: Fix the error requests for all of the routes.
    }
}