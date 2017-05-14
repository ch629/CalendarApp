package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;

public class EditEventControllerV2 extends ControlledView {

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
    	CalendarApp.closeSecondaryScene();
    }

    @FXML
    void comfirmClicked(ActionEvent event) {
    	CalendarApp.closeSecondaryScene();
    	
    }

}
