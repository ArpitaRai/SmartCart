package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import model.DatabaseConnector;
import animatefx.animation.Shake;

public class RegistrationController {

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
    private Label errorContact;

    @FXML
    private Label errorEmail;

    @FXML
    private Label errorName;

    @FXML
    private Label errorPassword;

    @FXML
    private Label errorUsername;


    @FXML
    void registerUser(ActionEvent event) {
        clearStylesAndMaskLabels();
        if (!validateNotEmpty(nameField, errorName, "Name should not be empty")) return;
        if (!validateWithPattern(contactField, errorContact, "^\\d{10}$", "Phone number should be valid")) return;
        if (!validateWithPattern(emailField, errorEmail, "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$", "Please Enter valid Email")) return;
        if (!validateNotEmpty(userIdField, errorUsername, "Username cannot be empty")) return;
        if (!validateNotEmpty(passwordField, errorPassword, "Password cannot be empty")) return;

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            showValidationError(passwordField, null, "Passwords need to match");
            applyErrorStyle(confirmPasswordField);
            new Shake(confirmPasswordField).play();
            return;
        }

        insertUserData(event);
    }

    private boolean validateNotEmpty(TextField textField, Label errorLabel, String errorMessage) {
        if (!textField.getText().trim().isEmpty()) {
            return true;
        }
        showValidationError(textField, errorLabel, errorMessage);
        return false;
    }

    private boolean validateWithPattern(TextField textField, Label errorLabel, String regex, String errorMessage) {
        if (Pattern.matches(regex, textField.getText())) {
            return true;
        }
        showValidationError(textField, errorLabel, errorMessage);
        return false;
    }

    private void showValidationError(TextField textField, Label errorLabel, String errorMessage) {
        applyErrorStyle(textField);
        if (errorLabel != null) {
            errorLabel.setVisible(true);
        }
        showDialog(errorMessage);
    }

    private void applyErrorStyle(TextField textField) {
        textField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
    }

    private void insertUserData(ActionEvent event) {
        String query = "insert into user_info values (?,?,?,?,?)";
        try (Connection conn = DatabaseConnector.getInstance();
             PreparedStatement preparedStmt = conn.prepareStatement(query)) {

            preparedStmt.setString(1, userIdField.getText());
            preparedStmt.setString(2, nameField.getText());
            preparedStmt.setString(3, contactField.getText());
            preparedStmt.setString(4, emailField.getText());
            preparedStmt.setString(5, passwordField.getText());
            preparedStmt.executeUpdate();

            showDialog("User Registration successful!");
            clearFieldValues();
            ScreenController.goToLoginPage(event);
        } catch (Exception e)
        		{
            System.out.println("Error Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void showDialog(String errorMessage) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Registration");
        ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.setContentText(errorMessage);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    private void clearStylesAndMaskLabels() {
        clearStyles();
        maskErrorLabels();
    }

    private void clearStyles() {
        nameField.setStyle(null);
        contactField.setStyle(null);
        emailField.setStyle(null);
        userIdField.setStyle(null);
        passwordField.setStyle(null);
        confirmPasswordField.setStyle(null);
    }

    private void maskErrorLabels() {
        errorContact.setVisible(false);
        errorEmail.setVisible(false);
        errorName.setVisible(false);
        errorPassword.setVisible(false);
        errorUsername.setVisible(false);
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
