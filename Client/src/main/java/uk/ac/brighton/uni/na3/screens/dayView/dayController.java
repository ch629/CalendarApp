package uk.ac.brighton.uni.na3.screens.dayView;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class dayController implements ControlledView {
	
	private ScreenController parent;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> table;

    @FXML
    void addEvent(ActionEvent event) {
    	CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Add New Event");
    }

    @FXML
    void nextDayPressed(ActionEvent event) {

    }

    @FXML
    void previousDayPressed(ActionEvent event) {

    }

	@Override
	public void setParent(ScreenController controller) {
		parent = controller;		
	}
	
	public void initialize(){
		datePicker.setValue(LocalDate.now());
	}

}
