package uk.ac.brighton.uni.na3.utils;


import uk.ac.brighton.uni.na3.model.NewEvent;
import uk.ac.brighton.uni.na3.model.networking.request.SingleDataRequest;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.util.ArrayList;
import java.util.List;

public class EventUtils {
    public static List<NewEvent> getEventsOnDay(long day) {
        final List<NewEvent> returnEvents = new ArrayList<>();
        NetworkUtils.post("event/day",
                new SingleDataRequest<>(AuthUtils.getAuthToken(), day), SingleDataResponse.class)
                .ifOK(res -> returnEvents.addAll((List<NewEvent>) res.getData()));
        return returnEvents;
    }
}
