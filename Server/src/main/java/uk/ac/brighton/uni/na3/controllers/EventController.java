package uk.ac.brighton.uni.na3.controllers;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.networking.request.PairDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.ResponseType;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.util.List;

@Controller("/event/")
@EnableAutoConfiguration
public class EventController {
    @PostMapping("/create")
    @ResponseBody
    Response createEvent(EventCreateRequest request) {
        return new Response(ResponseType.NOT_IMPLEMENTED);
    }

    @PostMapping("/get")
    @ResponseBody
    SingleDataResponse<Event> getEvent(SingleDataRequest<Integer> request) {
        return new SingleDataResponse<>(ResponseType.NOT_IMPLEMENTED);
    }

    @GetMapping("/between")
    @ResponseBody
    SingleDataResponse<List<Event>> getEventsBetween(PairDataRequest<Long, Long> request) { //TODO: Long could probably just be the dates, as Jackson serializes these to longs by default
        return new SingleDataResponse<>(ResponseType.NOT_IMPLEMENTED);
    }
}