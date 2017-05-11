package uk.ac.brighton.uni.na3.model.networking.request.event;

import uk.ac.brighton.uni.na3.model.networking.request.Request;

public class EventOnDayRequest extends Request {
    private int day, month, year;

    public EventOnDayRequest(char[] authToken, int day, int month, int year) {
        super(authToken);
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
