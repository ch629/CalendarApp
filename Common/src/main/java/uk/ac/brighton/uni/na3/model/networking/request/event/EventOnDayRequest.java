package uk.ac.brighton.uni.na3.model.networking.request.event;

import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.model.networking.request.Request;
import uk.ac.brighton.uni.na3.model.networking.response.SingleDataResponse;

import java.util.List;

public class EventOnDayRequest extends Request<SingleDataResponse<List<Event>>> {
    private int day, month, year;

    public EventOnDayRequest(char[] authToken, int day, int month, int year) {
        super(authToken);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public EventOnDayRequest(char[] authToken, SingleDataResponse<List<Event>> expectedResponse, int day, int month, int year) {
        super(authToken, expectedResponse);
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
