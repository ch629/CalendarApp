package uk.ac.brighton.uni.na3.screens.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.model.SimpleDateTime;
import uk.ac.brighton.uni.na3.utils.EventUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CreateEventController extends ControlledView {
    @FXML
    private TextField nameField;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField timeField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField durationField;

    @FXML
    private TextField attendeeField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button cancelButton;

    @FXML
    private Button confirmButton;

    @FXML
    void cancelClicked(ActionEvent event) {
        clearFields();
        CalendarApp.closeSecondaryScene();
    }

    @FXML
    void comfirmClicked(ActionEvent event) {
        String name, location, duration, description;
        LocalTime time;
        LocalDate date;
        String[] attendees;

        LocalDateTime startTime;
        LocalDateTime endTime;

        // Grab fields
        try {
            name = nameField.getText();
            location = locationField.getText();
            duration = durationField.getText();
            description = descriptionField.getText();
            time = LocalTime.parse(timeField.getText());
            date = datePicker.getValue();
            attendees = getAttendees();
            startTime = date.atTime(time);
            endTime = startTime.plusMinutes(Integer.parseInt(duration));
        } catch (Exception e) {
            System.out.printf("Error parsing fields: %s\n", e);
            return;
        }

        // Add event
        boolean res = EventUtils.createEvent(name, description, location, new SimpleDateTime(startTime), new SimpleDateTime(endTime), false, attendees);
        if (!res) {
            System.out.println("Error adding event");
            return;
        }

        clearFields();
        CalendarApp.closeSecondaryScene();

    }

    // Example input data "Lewis Allen,Constantinos Ioannou,Charlie Howes"
    private String[] getAttendees() {
        String attendee = attendeeField.getText();        //gets the attendeeField input
        String eventAttendee[] = attendee.split(",");    //it split the String attendee after
        //the comma and stores it in array

        // Trim results
        for (int i = 0; i < eventAttendee.length; i++)
            eventAttendee[i] = eventAttendee[i].trim();

        return eventAttendee;
    }

    private void clearFields() {
        nameField.clear();
        locationField.clear();
        timeField.clear();
        durationField.clear();
        attendeeField.clear();
        descriptionField.clear();
    }
}