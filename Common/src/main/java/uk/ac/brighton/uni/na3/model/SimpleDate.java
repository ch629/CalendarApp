package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

@JsonAutoDetect
public class SimpleDate implements Serializable {
    private static final long serialVersionUID = 1L;
    private int dayOfMonth, month, year;

    @JsonCreator
    public SimpleDate(@JsonProperty("dayOfMonth") int dayOfMonth,
                      @JsonProperty("month") int month,
                      @JsonProperty("year") int year) {
        this.dayOfMonth = dayOfMonth;
        this.month = month;
        this.year = year;
    }

    public SimpleDate(LocalDate localDate) {
        this(localDate.getDayOfMonth(), localDate.getMonthValue(), localDate.getYear());
    }

    public SimpleDate(Timestamp timestamp) {
        this(timestamp.toLocalDateTime().toLocalDate()); //TODO: This is a pretty gross way to do this.
    }

    @JsonIgnore
    public LocalDate toLocalDate() {
        return LocalDate.of(year, month, dayOfMonth);
    }

    @JsonIgnore
    public Timestamp toTimestamp() {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, dayOfMonth);
        return new Timestamp(cal.getTimeInMillis());
    }

    public void setDayOfMonth(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public SimpleDateTime toSimpleDateTime() {
        return new SimpleDateTime(dayOfMonth, month, year);
    }

    public void setDate(LocalDate localDate) {
        this.dayOfMonth = localDate.getDayOfMonth();
        this.month = localDate.getMonthValue();
        this.year = localDate.getYear();
    }
}