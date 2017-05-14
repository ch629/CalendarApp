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
import uk.ac.brighton.uni.na3.utils.AuthUtils;

public class LoginScreenController extends ControlledView {

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
        clearFields();
        getParent().setScreen(CalendarApp.createAccountID);
    }

    @FXML
    void loginButtonClicked(ActionEvent event) {
        outputText.setText("Attempting to login...");
        if (AuthUtils.login(userNameField.getText(), passwordField.getText().toCharArray())) {
            outputText.setText("");
            clearFields();
            CalendarApp.postLoginLoad();
            getParent().setScreen(CalendarApp.monthViewID);
            CalendarApp.resizeScreen();
        } else {
            outputText.setText("Incorrect Login Credentials");
        }
    }

    private void clearFields() {
        userNameField.clear();
        passwordField.clear();
    }
}
