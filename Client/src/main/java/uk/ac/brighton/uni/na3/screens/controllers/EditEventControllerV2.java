package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.screens.EventData;
import uk.ac.brighton.uni.na3.utils.EventUtils;

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
    	DayController c = (DayController) CalendarApp.getMainController().getScreen(CalendarApp.dayViewID).getValue();
    	EventData d = c.getSelectedEvent();
    	
    	d.setName(nameField.getText());
    	d.setTime(timeField.getText());
    	d.setLocation(locationField.getText());
    	d.setDesc(descriptionField.getText());
    	d.setDuration(durationField.getText());
    	d.setDate(datePicker.getValue());
    	
    	EventUtils.editEvent(d);
    	
    	c.dateChanged(null);
    	
    	CalendarApp.closeSecondaryScene();
    }

}
