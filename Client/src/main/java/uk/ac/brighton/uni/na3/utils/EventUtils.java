package uk.ac.brighton.uni.na3.utils;


import uk.ac.brighton.uni.na3.model.NewEvent;
import uk.ac.brighton.uni.na3.model.networking.request.event.EventOnDayRequest;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static List<NewEvent> getEventsOnDay(LocalDate date) {
        return getEventsOnDay(date.getDayOfMonth(), date.getMonthValue(), date.getYear());
    }

    public static List<NewEvent> getEventsOnDay(int day, int month, int year) {
        final List<NewEvent> returnEvents = new ArrayList<>();
        NetworkUtils.post("event/day",
                new EventOnDayRequest(AuthUtils.getAuthToken(), day, month, year), SingleDataResponse.class)
                .ifOK(res -> returnEvents.addAll((List<NewEvent>) res.getData()));
        return returnEvents;
    }
}
