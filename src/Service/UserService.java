package Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.regex.Pattern;

import animatefx.animation.Shake;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import model.DatabaseConnector;
import model.UserData;

public class UserService {

    private static final String PHONE_NUMBER_PATTERN = "^\\d{10}$";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";

    public boolean validateUserData(UserData userData) {
        if (userData.getName().isEmpty()) {
            showDialog("Name should not be empty");
            return false;
        }

        if (!Pattern.matches(PHONE_NUMBER_PATTERN, userData.getContact())) {
            showDialog("Phone number should be valid");
            return false;
        }

        if (!Pattern.matches(EMAIL_PATTERN, userData.getEmail())) {
            showDialog("Please enter a valid email");
            return false;
        }

        if (userData.getUsername().isEmpty()) {
            showDialog("Username cannot be empty");
            return false;
        }

        if (userData.getPassword().isEmpty()) {
            showDialog("Password cannot be empty");
            return false;
        }

        if (!userData.getPassword().equals(userData.getConfirmPassword())) {
            showDialog("Passwords need to match");
            return false;
        }

        return true;
    }

    public boolean insertUserData(UserData userData) {
        String query = "INSERT INTO user_info (user_name, full_name, contact_number, email, password) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnector.getInstance();
             PreparedStatement preparedStmt = conn.prepareStatement(query)) {

            preparedStmt.setString(1, userData.getUsername());
            preparedStmt.setString(2, userData.getName());
            preparedStmt.setString(3, userData.getContact());
            preparedStmt.setString(4, userData.getEmail());
            preparedStmt.setString(5, userData.getPassword());
            preparedStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error Message: " + e.getMessage());
            String s = e.getMessage();
            System.out.println("yep + " + s);
            if (s.contains("Duplicate entry")) {
                showDialog("User Registration failed. Profile already exists");
            }
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void showDialog(String message) {
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("Registration");
        ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        Stage stage = (Stage) dialog.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        dialog.showAndWait();
    }
}
