package uk.ac.brighton.uni.na3.model.networking.request.event;

import uk.ac.brighton.uni.na3.model.networking.request.Request;

public class EventGetRequest extends Request {
    private final int eventId;

    public EventGetRequest(char[] authToken, int eventId) {
        super(authToken);
        this.eventId = eventId;
    }

    public int getEventId() {
        return eventId;
    }
}
