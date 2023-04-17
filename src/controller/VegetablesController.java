package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.CartItem;
import model.Product;
import java.sql.ResultSet;
import model.DatabaseConnector;

public class VegetablesController extends ProductBaseController {

	@FXML
	Label lblBellPepperPrice;
	@FXML
	Label lblCarrotPrice;
	@FXML
	Label lblCauliflowerPrice;
	@FXML
	Label lblMushroomsPrice;
	@FXML
	Label lblOnionsPrice;
	@FXML
	Label lblPotatoesPrice;
	@FXML
	Label lblScallionsPrice;
	@FXML
	Label lblSpinachPrice;
	@FXML
	Label lblTomatoesPrice;

	@FXML
	Spinner<Integer> spinBellPepper;
	@FXML
	Spinner<Integer> spinCarrot;
	@FXML
	Spinner<Integer> spinCauliflower;
	@FXML
	Spinner<Integer> spinMushrooms;
	@FXML
	Spinner<Integer> spinOnions;
	@FXML
	Spinner<Integer> spinPotatoes;
	@FXML
	Spinner<Integer> spinScallions;
	@FXML
	Spinner<Integer> spinSpinach;
	@FXML
	Spinner<Integer> spinTomatoes;

	@FXML
	Button btnBellPepper;
	@FXML
	Button btnCarrot;
	@FXML
	Button btnCauliflower;
	@FXML
	Button btnMushrooms;
	@FXML
	Button btnOnions;
	@FXML
	Button btnPotatoes;
	@FXML
	Button btnScallions;
	@FXML
	Button btnSpinach;
	@FXML
	Button btnTomatoes;

	public void initialize() {

//    	Fetch the Vegetable Products information from the Vegetables catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Vegetables");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblBellPepperPrice.setText("$" + inventoryItems.get("VEG001").getPrice() + "/ct");
		lblCarrotPrice.setText("$" + inventoryItems.get("VEG002").getPrice() + "/lb");
		lblCauliflowerPrice.setText("$" + inventoryItems.get("VEG003").getPrice() + "/ct");
		lblMushroomsPrice.setText("$" + inventoryItems.get("VEG004").getPrice() + "/lb");
		lblOnionsPrice.setText("$" + inventoryItems.get("VEG005").getPrice() + "/lb");
		lblPotatoesPrice.setText("$" + inventoryItems.get("VEG006").getPrice() + "/lb");
		lblScallionsPrice.setText("$" + inventoryItems.get("VEG007").getPrice() + "/lb");
		lblSpinachPrice.setText("$" + inventoryItems.get("VEG008").getPrice() + "/bunch");
		lblTomatoesPrice.setText("$" + inventoryItems.get("VEG009").getPrice() + "/lb");

		spinBellPepper.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG001").getQuantity(), 0));

		spinCarrot.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG002").getQuantity(), 0));

		spinCauliflower.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG003").getQuantity(), 0));

		spinMushrooms.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG004").getQuantity(), 0));

		spinOnions.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG005").getQuantity(), 0));

		spinPotatoes.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG006").getQuantity(), 0));

		spinScallions.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG007").getQuantity(), 0));

		spinSpinach.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG008").getQuantity(), 0));

		spinTomatoes.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("VEG009").getQuantity(), 0));

		if (inventoryItems.get("VEG001").getQuantity() == 0) {
			setOutOfStockField(lblBellPepperPrice, spinBellPepper, btnBellPepper);
		}
		if (inventoryItems.get("VEG002").getQuantity() == 0) {
			setOutOfStockField(lblCarrotPrice, spinCarrot, btnCarrot);
		}
		if (inventoryItems.get("VEG003").getQuantity() == 0) {
			setOutOfStockField(lblCauliflowerPrice, spinCauliflower, btnCauliflower);
		}
		if (inventoryItems.get("VEG004").getQuantity() == 0) {
			setOutOfStockField(lblMushroomsPrice, spinMushrooms, btnMushrooms);
		}
		if (inventoryItems.get("VEG005").getQuantity() == 0) {
			setOutOfStockField(lblOnionsPrice, spinOnions, btnOnions);
		}
		if (inventoryItems.get("VEG006").getQuantity() == 0) {
			setOutOfStockField(lblPotatoesPrice, spinPotatoes, btnPotatoes);
		}
		if (inventoryItems.get("VEG007").getQuantity() == 0) {
			setOutOfStockField(lblScallionsPrice, spinScallions, btnScallions);
		}
		if (inventoryItems.get("VEG008").getQuantity() == 0) {
			setOutOfStockField(lblSpinachPrice, spinSpinach, btnSpinach);
		}
		if (inventoryItems.get("VEG009").getQuantity() == 0) {
			setOutOfStockField(lblTomatoesPrice, spinTomatoes, btnTomatoes);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnBellPepper")) {
			CartItem ci = new CartItem("VEG001", inventoryItems.get("VEG001").getProductName(),
					(Integer) spinBellPepper.getValue(), inventoryItems.get("VEG001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCarrot")) {
			CartItem ci = new CartItem("VEG002", inventoryItems.get("VEG002").getProductName(),
					(Integer) spinCarrot.getValue(), inventoryItems.get("VEG002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCauliflower")) {
			CartItem ci = new CartItem("VEG003", inventoryItems.get("VEG003").getProductName(),
					(Integer) spinCauliflower.getValue(), inventoryItems.get("VEG003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnMushrooms")) {
			CartItem ci = new CartItem("VEG004", inventoryItems.get("VEG004").getProductName(),
					(Integer) spinMushrooms.getValue(), inventoryItems.get("VEG004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnOnions")) {
			CartItem ci = new CartItem("VEG005", inventoryItems.get("VEG005").getProductName(),
					(Integer) spinOnions.getValue(), inventoryItems.get("VEG005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPotatoes")) {
			CartItem ci = new CartItem("VEG006", inventoryItems.get("VEG006").getProductName(),
					(Integer) spinPotatoes.getValue(), inventoryItems.get("VEG006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnScallions")) {
			CartItem ci = new CartItem("VEG007", inventoryItems.get("VEG007").getProductName(),
					(Integer) spinScallions.getValue(), inventoryItems.get("VEG007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnSpinach")) {
			CartItem ci = new CartItem("VEG008", inventoryItems.get("VEG008").getProductName(),
					(Integer) spinSpinach.getValue(), inventoryItems.get("VEG008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnTomatoes")) {
			CartItem ci = new CartItem("VEG009", inventoryItems.get("VEG009").getProductName(),
					(Integer) spinTomatoes.getValue(), inventoryItems.get("VEG009").getPrice());

			cart.addProduct(ci);

		}

	}

	// Traverse back to the Catalog Page
	@FXML
	private void backToCatalog(javafx.event.ActionEvent event) {
		ScreenController.goToCatalogPage(event);
	}

	// Go to the Order Summary Page
	@FXML
	private void goToCart(javafx.event.ActionEvent event) {
		ScreenController.goToCartPage(event);
	}

	// Logout of the application and return to the Login Page
	@FXML
	void goToLogin(ActionEvent event) {
		logOff();
		ScreenController.goToLoginPage(event);
	}

	// Disable the product item on the screen if it goes out of stock 
	private void setOutOfStockField(Label errorLabel, Spinner spinner, Button bt) {
		errorLabel.setText("Out of Stock");
		errorLabel.setTextFill(Color.RED);
		spinner.setDisable(true);
		bt.setDisable(true);
	}

}
