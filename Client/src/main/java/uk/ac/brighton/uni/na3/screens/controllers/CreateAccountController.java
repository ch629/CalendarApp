package uk.ac.brighton.uni.na3.screens.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uk.ac.brighton.uni.na3.CalendarApp;
import uk.ac.brighton.uni.na3.ControlledView;
import uk.ac.brighton.uni.na3.utils.AuthUtils;

public class CreateAccountController extends ControlledView {
    @FXML
    private Label outputText;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button createAccountButton;
    
    @FXML
    private Button backToLogin;

    @FXML
    void backToLoginClicked(ActionEvent event) {
    	userNameField.clear();
        passwordField.clear();
        getParent().setScreen(CalendarApp.loginScreenID);
        CalendarApp.resizeScreen();
    }

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
            getParent().setScreen(CalendarApp.monthViewID);
            CalendarApp.resizeScreen();
        } else {
        	outputText.setText("Error Logging in");
        }
    }
}
