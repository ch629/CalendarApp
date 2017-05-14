package uk.ac.brighton.uni.na3.utils;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.SimpleDate;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventCreateRequest;
import uk.ac.brighton.uni.na3.model.networking.response.Response;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;
import uk.ac.brighton.uni.na3.screens.EventData;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static List<Event> getEventsOnDay(LocalDate date) {
        return getEventsOnDay(new SimpleDate(date));
    }

    public static List<Event> getEventsOnDay(SimpleDate date) {
        ObjectMapper mapper = new ObjectMapper();
        final List<Event> returnEvents = new ArrayList<>();
        NetworkUtils.post("event/day",
                new SingleDataRequest<>(AuthUtils.getAuthToken(), date), SingleDataResponse.class)
                .ifOK(res ->
                        returnEvents.addAll(mapper.convertValue(res.getData(), new TypeReference<List<Event>>() {})));
        return returnEvents;
    }

    public static boolean createEvent(String title, String description, String location, SimpleDateTime start, SimpleDateTime end, boolean isPrivate, String[] attendees) {
        final boolean[] ret = {false};
        EventCreateRequest request = new EventCreateRequest(AuthUtils.getAuthToken(), title, description, location, start, end, isPrivate, attendees);
        NetworkUtils.post("event/create", request, SingleDataResponse.class).ifOK(res -> ret[0] = true);
        return ret[0];
    }

    public static boolean editEvent(EventData event) {
        final boolean[] ret = {false};
        NetworkUtils.post("event/edit", new SingleDataRequest<>(AuthUtils.getAuthToken(), event.toCommonEvent()), Response.class)
                .ifOK(res -> ret[0] = true);
        return ret[0];
    }

    public static boolean removeEvent(int eventId) {
        final boolean[] ret = {false};
        NetworkUtils.post("event/delete", new SingleDataRequest<>(AuthUtils.getAuthToken(), eventId), Response.class)
                .ifOK(res -> ret[0] = true);
        return ret[0];
    }
}
