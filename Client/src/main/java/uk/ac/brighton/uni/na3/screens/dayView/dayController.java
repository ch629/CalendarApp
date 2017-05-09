package uk.ac.brighton.uni.na3.screens.dayView;

import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class dayController implements ControlledView {
	
	private ScreenController parent;
	//private ArrayList<Event> events;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TableView<?> table;

    @FXML
    void addEvent(ActionEvent event) {
    	CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Add New Event");
    }
    
    @FXML
    void dateChanged(ActionEvent event) {
    	table.setPlaceholder(new Label(datePicker.getValue().equals(LocalDate.now()) ? "You have no events today." : "You have no events planned for this day."));
    }

    @FXML
    void nextDayPressed(ActionEvent event) {
    	datePicker.setValue( datePicker.getValue().plusDays(1) );
    }

    @FXML
    void previousDayPressed(ActionEvent event) {
    	datePicker.setValue( datePicker.getValue().minusDays(1) );
    }

	@Override
	public void setParent(ScreenController controller) {
		parent = controller;		
	}
	
	public void initialize(){
		datePicker.setValue(LocalDate.now());
		table.setPlaceholder(new Label("You have no events planned for today."));
	}
	
	

}
