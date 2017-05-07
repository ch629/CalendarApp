package uk.ac.brighton.uni.na3.screens.editEvent;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class EditEventController implements ControlledView {
	
	private ScreenController parentController; 
	
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

	@Override
	public void setParent(ScreenController controller) {
		parentController = controller;	
	}
}