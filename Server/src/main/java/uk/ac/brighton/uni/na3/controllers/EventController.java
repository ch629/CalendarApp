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
import uk.ac.brighton.uni.na3.model.networking.request.PairDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.sql.Timestamp;
import java.util.List;

@Controller("/event/")
@EnableAutoConfiguration
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    @ResponseBody
    SingleDataResponse<Integer> createEvent(EventCreateRequest request) { //TODO: Probably need to return the newly created Event or at least it's ID for the client to do stuff with it
        Event newEvent = Event.fromCreateRequest(request);
        if (newEvent == null)
            return new SingleDataResponse<>(ResponseType.BAD_REQUEST); //TODO: Maybe INVALID_PARAMETERS ResponseType
        newEvent = eventService.create(newEvent); //TODO: Send invites to the set attendees
        return new SingleDataResponse<>(ResponseType.OK, newEvent.getEventId());
    }

    @PostMapping("/get")
    @ResponseBody
    SingleDataResponse<Event> getEvent(SingleDataRequest<Integer> request) {
        Event event = eventService.findById(request.getData());
        if (event == null) return new SingleDataResponse<>(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(ResponseType.OK, event);
    }

    @GetMapping("/between")
    @ResponseBody
    SingleDataResponse<List<Event>> getEventsBetween(PairDataRequest<Long, Long> request) { //TODO: Long could probably just be the dates, as Jackson serializes these to longs by default
        User user = AuthTokenManager.instance.getUser(request.getAuthToken());
        Timestamp start = new Timestamp(request.getFirst()), end = new Timestamp(request.getSecond());
        List<Event> events = eventService.findDatesOverlapping(start, end, user.getUsername());
        if (events == null) return new SingleDataResponse<>(ResponseType.NOT_FOUND);
        return new SingleDataResponse<>(ResponseType.NOT_IMPLEMENTED);
    }
}