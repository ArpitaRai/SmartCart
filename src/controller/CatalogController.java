package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
//import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class CatalogController extends ProductBaseController {

	@FXML
	private Label lblUserName;

	// CatalogController object is used to traverse across the Screens and Welcome the User post successful Login
	@Override
	public void initialize() {
		lblUserName.setText("Hello " + userName + "! Please select the category and add to cart. ");
	}

	@FXML
	public void FruitsPage(ActionEvent event) {

		ScreenController.goToFruitsPage(event);
	}

	@FXML
	public void VegetablesPage(ActionEvent event) {

		ScreenController.goToVegetablesPage(event);
	}

	@FXML
	public void SnacksPage(ActionEvent event) {

		ScreenController.goToSnacksPage(event);
	}

	@FXML
	public void DairyPage(ActionEvent event) {

		ScreenController.goToDairyPage(event);
	}
	
	@FXML
	public void DrinksPage(ActionEvent event) {

		ScreenController.goToDrinksPage(event);
	}

	@FXML
	public void MeatPage(ActionEvent event) {

		ScreenController.goToMeatPage(event);
	}

	@FXML
	void goToLogin(ActionEvent event) {
		logOut();
		ScreenController.goToLoginPage(event);
	}

	@FXML
	public void goToCatalogExpenses(ActionEvent event) {

		ScreenController.goToCatalogExpensesPage(event);
	}

	@FXML
	void goToOrderHistory(ActionEvent event) {
		ScreenController.goToOrderHistoryPage(event);
	}

}
