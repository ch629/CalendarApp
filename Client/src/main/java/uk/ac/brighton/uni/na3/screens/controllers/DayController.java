package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;
import uk.ac.brighton.uni.na3.model.Event;
import uk.ac.brighton.uni.na3.screens.EventData;
import uk.ac.brighton.uni.na3.utils.AuthUtils;
import uk.ac.brighton.uni.na3.utils.EventUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    private Button editEventButton;

    @FXML
    void Logout(ActionEvent event) {
    	AuthUtils.resetToken();
    	parent.setScreen(CalendarApp.loginScreenID);
    	parent.unloadScreen(CalendarApp.dayViewID);
    	CalendarApp.resizeScreen();
    }
    
    @FXML
    void editEvent(ActionEvent event) {
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

        ObservableList<EventData> eventsInTable = table.getItems();
        List<Event> eventsToDisplay = EventUtils.getEventsOnDay(datePicker.getValue());
        
        for(Event e : eventsToDisplay){
        	LocalDateTime startTime = e.getStartDate().toLocalDateTime();
        	LocalDateTime endTime = e.getEndDate().toLocalDateTime();
        	   	
        	EventData eventToAdd = new EventData(e.getTitle(),
        						  	             startTime.toLocalTime(),
        							             e.getDescription(),
        							             Integer.toString(minutesBetweenDates(startTime, endTime)),
        							             e.getLocation());
        	
        	eventsInTable.add(eventToAdd);
        }

        /*eventsInTable.addAll(
                eventsToDisplay.stream()
                        .map(EventData::new)
                        .collect(Collectors.toList()));*/
        
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
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        // DatePickerSkin datePickerSkin = new DatePickerSkin(datePicker);
        // Node popupContent = datePickerSkin.getPopupContent();

        dateChanged(null);
    }
    
    public LocalDate getDate(){
    	return datePicker.getValue();
    }

    private int minutesBetweenDates(LocalDateTime start, LocalDateTime end){
    	long days    = start.until(end, ChronoUnit.DAYS);
    	long hours   = start.until(end, ChronoUnit.HOURS);
    	long minutes = start.until(end, ChronoUnit.MINUTES);
    	
    	return (int) ((days * 1440) + (hours * 60) + minutes);
    }
}
