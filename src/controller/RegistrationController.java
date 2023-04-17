package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.UserData;
import Service.UserService;

public class RegistrationController {

    private UserService userService = new UserService();

    @FXML
    private Button backToLogin;

    @FXML
    private Button submit_button;

    @FXML
    private TextField contactField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField userIdField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    void registerUser(ActionEvent event) {
        UserData userData = new UserData(nameField.getText(), contactField.getText(), emailField.getText(),
            userIdField.getText(), passwordField.getText(), confirmPasswordField.getText());

        if (userService.validateUserData(userData) && userService.insertUserData(userData)) {
            //userService.insertUserData(userData);
            userService.showDialog("User Registration successful!");
            clearFieldValues();
            ScreenController.goToLoginPage(event);
        }
    }

    private void clearFieldValues() {
        nameField.setText(null);
        contactField.setText(null);
        emailField.setText(null);
        userIdField.setText(null);
        passwordField.setText(null);
        confirmPasswordField.setText(null);
    }

    @FXML
    void goToLogin(ActionEvent event) {
        ScreenController.goToLoginPage(event);
    }
}
