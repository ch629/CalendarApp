package uk.ac.brighton.uni.na3.screens.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.screens.EventData;
import uk.ac.brighton.uni.na3.utils.AuthUtils;
import uk.ac.brighton.uni.na3.utils.EventUtils;

public class MonthViewController extends ControlledView{
	
	private EventData selectedEvent;

    @FXML
    private Label monthViewDate;

    @FXML
    private TableView<EventData> table;

    @FXML
    private TableColumn<EventData, String> nameCol;

    @FXML
    private TableColumn<EventData, String> timeCol;

    @FXML
    private TableColumn<EventData, String> durationCol;

    @FXML
    private TableColumn<EventData, String> locationCol;

    @FXML
    private TableColumn<EventData, String> descCol;

    @FXML
    private DatePicker datePicker;

    @FXML
    void addEvent(ActionEvent event) {
    	CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Add New Event");
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
    void dateChanged(ActionEvent event) {
    	table.setPlaceholder(new Label(datePicker.getValue().equals(LocalDate.now()) ? "You have no events today."
                : "You have no events planned for this day."));
    	
    	monthViewDate.setText( generateMonthText() );

        table.getItems().clear();
        ObservableList<EventData> eventsInTable = table.getItems();
        List<Event> eventsToDisplay = EventUtils.getEventsOnDay(datePicker.getValue());

        eventsInTable.addAll(
                eventsToDisplay.stream()
                        .map(EventData::new)
                        .collect(Collectors.toList()));
    }

    @FXML
    void logout(ActionEvent event) {
    	AuthUtils.resetToken();
        getParent().setScreen(CalendarApp.loginScreenID);
        getParent().unloadScreen(CalendarApp.dayViewID);
        CalendarApp.resizeScreen();
    }

    @FXML
    void nextMonthPressed(ActionEvent event) {
    	datePicker.setValue(datePicker.getValue().plusMonths(1));
    }

    @FXML
    void previousMonthPressed(ActionEvent event) {
    	datePicker.setValue(datePicker.getValue().minusMonths(1));
    }

    @FXML
    void remove(ActionEvent event) {
    	EventData e = table.getSelectionModel().getSelectedItem();
    	if( e == null )
    		return;
    	
    	EventUtils.removeEvent(e.getID());
    	dateChanged(null);
    }

    public EventData getSelectedEvent(){
    	return selectedEvent;
    }
    
    public void initialize() {
        datePicker.setValue(LocalDate.now());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        dateChanged(null);
    }
    
    private String generateMonthText(){
    	DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM yyyy");
    	return df.format(datePicker.getValue());
    }
}

