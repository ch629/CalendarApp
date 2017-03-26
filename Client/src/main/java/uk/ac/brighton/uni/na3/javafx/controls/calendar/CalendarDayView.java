package uk.ac.brighton.uni.na3.javafx.controls.calendar;

import javafx.scene.layout.GridPane;

public class CalendarDayView extends GridPane { //TODO: Don't inherit && use composition? As we don't want to allow adding columns manually to this.
    //TODO: Look at HBox & VBox as well -> Could potentially work better.
    public CalendarDayView() {
        for (int i = 0; i < 23; i++) //TODO: Maybe make these half-hourly cells -> Add extra rows within the rows, to split them?
            this.addRow(i);

        addColumn(0); //TODO: Check these (First column will specify the time
        addColumn(1); //TODO: This will show the hour event
        //TODO: Check how to do events that go over one hour, or just add the event to each cell then somehow merge them?
    }
}
