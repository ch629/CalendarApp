package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.ControlledView;

public class EditEventController extends ControlledView {
    @FXML
    private TextField nameField;

    @FXML
    private Button editButton;

    @FXML
    private TextField timeField;

    @FXML
    private TextField dateField;

    @FXML
    private TextArea descriptionField;

    @FXML
    void editClicked(ActionEvent event) {

    }
}