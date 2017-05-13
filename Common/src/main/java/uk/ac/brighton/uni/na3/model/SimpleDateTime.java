package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@JsonAutoDetect
public class SimpleDateTime extends SimpleDate {
    private int hour = 0, minute = 0;

    @JsonCreator
    public SimpleDateTime(@JsonProperty("dayOfMonth") int dayOfMonth,
                          @JsonProperty("month") int month,
                          @JsonProperty("year") int year,
                          @JsonProperty("hour") int hour,
                          @JsonProperty("minute") int minute) {
        super(dayOfMonth, month, year);
        this.hour = hour;
        this.minute = minute;
    }

    public SimpleDateTime(int dayOfMonth, int month, int year) {
        super(dayOfMonth, month, year);
    }

    public SimpleDateTime(LocalDate localDate) {
        super(localDate);
    }

    public SimpleDateTime(LocalDate localDate, int hour, int minute) {
        super(localDate);
        this.hour = hour;
        this.minute = minute;
    }

    public SimpleDateTime(LocalDateTime dateTime) {
        this(dateTime.getDayOfMonth(), dateTime.getMonthValue(), dateTime.getYear(),
                dateTime.getHour(), dateTime.getMinute());
    }

    public SimpleDateTime(Timestamp timestamp) {
        super(timestamp);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timestamp.getTime());
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    @JsonIgnore
    public Timestamp toTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.set(getYear(), getMonth(), getDayOfMonth(), getHour(), getMinute());
        return new Timestamp(cal.getTimeInMillis());
    }
}
