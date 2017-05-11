package uk.ac.brighton.uni.na3.screens.dayView;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.sun.javafx.scene.control.skin.DatePickerSkin;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;
import uk.ac.brighton.uni.na3.model.NewEvent;
import uk.ac.brighton.uni.na3.screens.EventData;
import uk.ac.brighton.uni.na3.utils.EventUtils;

public class DayController implements ControlledView {

	private ScreenController parent;

	@FXML
	private DatePicker datePicker;

	@FXML
	private TableView<EventData> table;
	
    @FXML
    private TableColumn<EventData, String> nameCol;

    @FXML
    private TableColumn<EventData, String> timeCol;

    @FXML
    private TableColumn<EventData, String> descCol;
    
    @FXML
    private TableColumn<EventData, String> durationCol;

    @FXML
    private TableColumn<EventData, String> locationCol;

	@FXML
	void addEvent(ActionEvent event) {
		CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Add New Event");
	}

	@FXML
	void dateChanged(ActionEvent e) {
		table.setPlaceholder(new Label(datePicker.getValue().equals(LocalDate.now()) ? "You have no events today."
				: "You have no events planned for this day."));
		
	    ObservableList<EventData> eventsInTable = table.getItems();
	    List<NewEvent> eventsToDisplay = EventUtils.getEventsOnDay(datePicker.getValue());
	    
	    for( NewEvent n : eventsToDisplay ){
	    	EventData event = new EventData(n.getDescription(), LocalTime.now(), n.getDescription(), n.getDescription(), n.getLocation() );
	    	eventsInTable.add(event);
	    }
	    
	    //TODO: Grab events from server and insert into table.getItems()
	}

	@FXML
	void nextDayPressed(ActionEvent event) {
		datePicker.setValue(datePicker.getValue().plusDays(1));
	}

	@FXML
	void previousDayPressed(ActionEvent event) {
		datePicker.setValue(datePicker.getValue().minusDays(1));
	}

	@Override
	public void setParent(ScreenController controller) {
		parent = controller;
	}

	public void initialize() {
		datePicker.setValue(LocalDate.now());
		nameCol.setCellValueFactory(new PropertyValueFactory<EventData,String>("name"));
		timeCol.setCellValueFactory(new PropertyValueFactory<EventData,String>("time"));
		descCol.setCellValueFactory(new PropertyValueFactory<EventData,String>("desc"));
		durationCol.setCellValueFactory(new PropertyValueFactory<EventData,String>("duration"));
		locationCol.setCellValueFactory(new PropertyValueFactory<EventData,String>("location"));
		
		// DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
		// Node popupContent = datePickerSkin.getPopupContent();
		
		dateChanged(null);
	}

}
