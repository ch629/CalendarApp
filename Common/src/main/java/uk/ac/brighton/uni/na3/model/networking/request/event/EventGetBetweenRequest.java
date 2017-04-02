package uk.ac.brighton.uni.na3.model.networking.request.event;

import uk.ac.brighton.uni.na3.model.networking.request.Request;

public class EventGetBetweenRequest extends Request {
    private final long startDate, endDate;

    public EventGetBetweenRequest(char[] authToken, long startDate, long endDate) { //TODO: This could be Dates, as Jackson by default serializes these a longs
        super(authToken);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public long getStartDate() {
        return startDate;
    }

    public long getEndDate() {
        return endDate;
    }
}
