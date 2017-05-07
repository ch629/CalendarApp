package uk.ac.brighton.uni.na3.screens.login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;

public class LoginScreenController implements ControlledView {
	
	private ScreenController parentController; 

    @FXML
    private TextField userNameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label createAccountButton;

    @FXML
    void createNewAccountClick(MouseEvent event) {

    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
    	parentController.setScreen(CalendarApp.calendarViewID);
    }

	@Override
	public void setParent(ScreenController controller) {
		parentController = controller;
	}

}
