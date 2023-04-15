package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.DatabaseConnector;

public class LoginController extends ProductBaseController {

    @FXML private TextField username_text;
    @FXML private PasswordField password_text;
    @FXML private Label password_label;
    @FXML private Label username_label;
    @FXML private Button register_button;
    @FXML private Button signIn_button;
    @FXML private Label password_error;
    @FXML private Label username_error;

    @Override
    void initialize() {
        clearFields();
    }

    // Validate the user credentials and go to Catalog Page
    @FXML
    public void goToCatalog(ActionEvent event) {
        try {
            Connection conn = DatabaseConnector.getInstance();
            Statement st = conn.createStatement();
            String query = "SELECT password, full_name FROM user_info WHERE user_name = '" + username_text.getText() + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                username_text.setStyle(null);
                username_error.setVisible(false);
                if (storedPassword.equals(password_text.getText())) {
                    // Pop up login successful
                    userId = username_text.getText();
                    userName = rs.getString("full_name");
                    password_text.setBorder(null);
                    password_text.setStyle(null);
                    password_error.setVisible(false);
                    showSuccessDialog("Login successful!");
                    ScreenController.goToCatalogPage(event);
                } else {
                    // Throw error saying Invalid Password.
                    password_text.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                    new animatefx.animation.Shake(password_text).play();
                    password_error.setVisible(true);
                    showErrorDialog("Incorrect Password");
                    System.out.println("Incorrect Password");
                }
            } else {
                username_text.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                new animatefx.animation.Shake(username_text).play();
                username_error.setVisible(true);
                showErrorDialog("Incorrect Username");
            }
            st.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Go to the User Registration Page
    @FXML
    public void RegistrationPage(ActionEvent event) {
        ScreenController.goToRegistrationPage(event);
    }

    private void clearFields() {
        username_text.setText(null);
        password_text.setText(null);
    }

    private void showSuccessDialog(String message) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Login");
        ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }

    private void showErrorDialog(String message) {
        Dialog<String> dialog = new Dialog<String>();
        dialog.setTitle("Login");
        ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.setContentText(message);
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.showAndWait();
    }
}
