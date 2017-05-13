package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.ScreenController;
import uk.ac.brighton.uni.na3.utils.AuthUtils;

public class CreateAccountController implements ControlledView {

	private ScreenController parent;
	
    @FXML
    private Label outputText;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createAccountButton;

    @FXML
    void createAccountButtonClicked(ActionEvent event) {
    	outputText.setText("Creating new account...");
        System.out.printf("Running create with --- username: %s, password %s\n", userNameField.getText(), passwordField.getText());
        boolean res = AuthUtils.register(userNameField.getText(), passwordField.getText().toCharArray());
        outputText.setText(res ? "Account created successfully." : "Unable to create account." );
        outputText.setText("Attempting to login...");
        System.out.printf("Running login with --- username: %s, password %s\n", userNameField.getText(), passwordField.getText());
        res = AuthUtils.login(userNameField.getText(), passwordField.getText().toCharArray());
        if(res) {
	        outputText.setText("");
	        userNameField.clear();
	        passwordField.clear();
	    	CalendarApp.postLoginLoad();
	        parent.setScreen(CalendarApp.dayViewID);
	        CalendarApp.resizeScreen();
        } else {
        	outputText.setText("Error Logging in");
        }
    }
    
    @Override
    public void setParent(ScreenController controller) {
        parent = controller;
    }

}
