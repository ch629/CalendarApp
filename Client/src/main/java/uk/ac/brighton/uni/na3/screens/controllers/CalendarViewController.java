package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class CalendarViewController implements ControlledView {

    ScreenController parentController;

    @FXML
    private Color x232;

    @FXML
    private Color x231;

    @FXML
    private Color x23;

    @FXML
    private Font x12;

    @FXML
    private Color x22;

    @FXML
    void monday1(MouseEvent event) {
        CalendarApp.newSecondaryScene(CalendarApp.createEventID, "Create Event");
    }

    @Override
    public void setParent(ScreenController controller) {
        parentController = controller;
    }

}
