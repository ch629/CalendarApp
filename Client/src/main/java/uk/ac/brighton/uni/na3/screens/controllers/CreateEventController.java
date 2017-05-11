package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class CreateEventController implements ControlledView {

    private ScreenController parentController;

    @FXML
    private TextField nameField;

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
        CalendarApp.closeSecondaryScene();
    }

    @FXML
    void comfirmClicked(ActionEvent event) {
        CalendarApp.closeSecondaryScene();
    }

    @Override
    public void setParent(ScreenController controller) {
        parentController = controller;
    }

    // Example input data "Lewis Allen,Constantinos Ioannou,Charlie Howes"
    public String[] getAttendees() {
        String attendee = attendeeField.getText();        //gets the attendeeField input
        String eventAttendee[] = attendee.split(",");    //it split the String attendee after
        //the comma and stores it in array
        return eventAttendee;
    }
}