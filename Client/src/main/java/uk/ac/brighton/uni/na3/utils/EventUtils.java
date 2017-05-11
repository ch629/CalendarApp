package uk.ac.brighton.uni.na3.utils;


import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.SimpleDate;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static List<Event> getEventsOnDay(LocalDate date) {
        return getEventsOnDay(new SimpleDate(date));
    }

    public static List<Event> getEventsOnDay(SimpleDate date) {
        final List<Event> returnEvents = new ArrayList<>();
        NetworkUtils.post("event/day",
                new SingleDataRequest<>(AuthUtils.getAuthToken(), date), SingleDataResponse.class)
                .ifOK(res -> returnEvents.addAll((List<Event>) res.getData()));
        return returnEvents;
    }
}
