package uk.ac.brighton.uni.na3.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;

@JsonAutoDetect
public class SimpleDate implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int dayOfMonth, month, year;

    @JsonCreator
    public SimpleDate(int dayOfMonth, int month, int year) {
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

    public int getDayOfMonth() {
        return dayOfMonth;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}