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

public class LoginScreenController implements ControlledView {

    private ScreenController parent;

    @FXML
    private TextField userNameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label createAccountButton;
    
    @FXML
    private Label outputText;

    @FXML
    void createNewAccountClick(MouseEvent event) {
        System.out.printf("Running create with --- username: %s, password %s\n", userNameField.getText(), passwordField.getText());
        AuthUtils.register(userNameField.getText(), passwordField.getText().toCharArray());
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        System.out.printf("Running login with --- username: %s, password %s\n", userNameField.getText(), passwordField.getText());
        if (AuthUtils.login(userNameField.getText(), passwordField.getText().toCharArray())) {
            outputText.setText("Successful Login");
        	CalendarApp.postLoginLoad();
            parent.setScreen(CalendarApp.dayViewID);
            CalendarApp.resizeScreen();
        } else {
            outputText.setText("Incorrect Login Credentials");
        }
    }

    @Override
    public void setParent(ScreenController controller) {
        parent = controller;
    }

}