package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.screens.EventData;
import uk.ac.brighton.uni.na3.utils.AuthUtils;
import uk.ac.brighton.uni.na3.utils.EventUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class DayController extends ControlledView {
	
	private EventData selectedEvent; // Stored selected event
	
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
    private Button editEventButton;
    
    @FXML
    private Button RemoveButton;
    
    @FXML
    void remove(ActionEvent event) {
    	EventData e = table.getSelectionModel().getSelectedItem();
    	if( e == null )
    		return;
    	
    	EventUtils.removeEvent(e.getID());
    	dateChanged(null);
    }

    @FXML
    void Logout(ActionEvent event) {
        AuthUtils.resetToken();
        getParent().setScreen(CalendarApp.loginScreenID);
        getParent().unloadScreen(CalendarApp.dayViewID);
        CalendarApp.resizeScreen();
    }

    @FXML
    void editEvent(ActionEvent event) {
    	EventData e = table.getSelectionModel().getSelectedItem();
    	if( e == null )
    		return;
    	
    	selectedEvent = e;
    	
        CalendarApp.newSecondaryScene(CalendarApp.editEventID, "Edit Event");
    }

    @FXML
    void addEvent(ActionEvent event) {
        CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Add New Event");
    }

    @FXML
    void dateChanged(ActionEvent event) {
        table.setPlaceholder(new Label(datePicker.getValue().equals(LocalDate.now()) ? "You have no events today."
                : "You have no events planned for this day."));

        table.getItems().clear();
        ObservableList<EventData> eventsInTable = table.getItems();
        List<Event> eventsToDisplay = EventUtils.getEventsOnDay(datePicker.getValue());

        eventsInTable.addAll(
                eventsToDisplay.stream()
                        .map(EventData::new)
                        .collect(Collectors.toList()));
    }

    @FXML
    void nextDayPressed(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().plusDays(1));
    }

    @FXML
    void previousDayPressed(ActionEvent event) {
        datePicker.setValue(datePicker.getValue().minusDays(1));
    }


    public void initialize() {
        datePicker.setValue(LocalDate.now());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        // DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        // Node popupContent = datePickerSkin.getPopupContent();

        dateChanged(null);
    }

    public LocalDate getDate() {
        return datePicker.getValue();
    }
    
    public EventData getSelectedEvent(){
    	return selectedEvent;
    }
}
