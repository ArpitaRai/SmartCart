package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.Cart;
import model.CartItem;
import model.Product;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import model.DatabaseConnector;

public class SnacksController extends ProductBaseController {

	@FXML
	Label lblBlueberryBarPrice;
	@FXML
	Label lblCheetosPrice;
	@FXML
	Label lblChipsPrice;
	@FXML
	Label lblChocolatePrice;
	@FXML
	Label lblOnionDipPrice;
	@FXML
	Label lblPopcornPrice;
	@FXML
	Label lblPringlesPrice;
	@FXML
	Label lblTostitosPrice;
	@FXML
	Label lblTrailPrice;

	@FXML
	Spinner<Integer> spinBlueberryBar;
	@FXML
	Spinner<Integer> spinCheetos;
	@FXML
	Spinner<Integer> spinChips;
	@FXML
	Spinner<Integer> spinChocolate;
	@FXML
	Spinner<Integer> spinOnionDip;
	@FXML
	Spinner<Integer> spinPopcorn;
	@FXML
	Spinner<Integer> spinPringles;
	@FXML
	Spinner<Integer> spinTostitos;
	@FXML
	Spinner<Integer> spinTrail;

	@FXML
	Button btnBlueberryBar;
	@FXML
	Button btnCheetos;
	@FXML
	Button btnChips;
	@FXML
	Button btnChocolate;
	@FXML
	Button btnOnionDip;
	@FXML
	Button btnPopcorn;
	@FXML
	Button btnPringles;
	@FXML
	Button btnTostitos;
	@FXML
	Button btnTrail;

	public void initialize() {

//   	Fetch the Snacks Products information from the Snacks catalog in the Database	
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Snacks");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblBlueberryBarPrice.setText("$" + inventoryItems.get("SNA001").getPrice() + "/ct");
		lblCheetosPrice.setText("$" + inventoryItems.get("SNA002").getPrice() + "/ct");
		lblChipsPrice.setText("$" + inventoryItems.get("SNA003").getPrice() + "/ct");
		lblChocolatePrice.setText("$" + inventoryItems.get("SNA004").getPrice() + "/ct");
		lblOnionDipPrice.setText("$" + inventoryItems.get("SNA005").getPrice() + "/ct");
		lblPopcornPrice.setText("$" + inventoryItems.get("SNA006").getPrice() + "/ct");
		lblPringlesPrice.setText("$" + inventoryItems.get("SNA007").getPrice() + "/ct");
		lblTostitosPrice.setText("$" + inventoryItems.get("SNA008").getPrice() + "/ct");
		lblTrailPrice.setText("$" + inventoryItems.get("SNA009").getPrice() + "/ct");

		spinBlueberryBar.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA001").getQuantity(), 0));

		spinCheetos.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA002").getQuantity(), 0));

		spinChips.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA003").getQuantity(), 0));

		spinChocolate.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA004").getQuantity(), 0));

		spinOnionDip.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA005").getQuantity(), 0));

		spinPopcorn.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA006").getQuantity(), 0));

		spinPringles.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA007").getQuantity(), 0));

		spinTostitos.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA008").getQuantity(), 0));

		spinTrail.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("SNA009").getQuantity(), 0));

		if (inventoryItems.get("SNA001").getQuantity() == 0) {
			setOutOfStockField(lblBlueberryBarPrice, spinBlueberryBar, btnBlueberryBar);
		}
		if (inventoryItems.get("SNA002").getQuantity() == 0) {
			setOutOfStockField(lblCheetosPrice, spinCheetos, btnCheetos);
		}
		if (inventoryItems.get("SNA003").getQuantity() == 0) {
			setOutOfStockField(lblChipsPrice, spinChips, btnChips);
		}
		if (inventoryItems.get("SNA004").getQuantity() == 0) {
			setOutOfStockField(lblChocolatePrice, spinChocolate, btnChocolate);
		}
		if (inventoryItems.get("SNA005").getQuantity() == 0) {
			setOutOfStockField(lblOnionDipPrice, spinOnionDip, btnOnionDip);
		}
		if (inventoryItems.get("SNA006").getQuantity() == 0) {
			setOutOfStockField(lblPopcornPrice, spinPopcorn, btnPopcorn);
		}
		if (inventoryItems.get("SNA007").getQuantity() == 0) {
			setOutOfStockField(lblPringlesPrice, spinPringles, btnPringles);
		}
		if (inventoryItems.get("SNA008").getQuantity() == 0) {
			setOutOfStockField(lblTostitosPrice, spinTostitos, btnTostitos);
		}
		if (inventoryItems.get("SNA009").getQuantity() == 0) {
			setOutOfStockField(lblTrailPrice, spinTrail, btnTrail);
		}

	}

//	Add selected products to the cart list
	@FXML
	private void addToCart(ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnBlueberryBar")) {
			CartItem ci = new CartItem("SNA001", inventoryItems.get("SNA001").getProductName(),
					(Integer) spinBlueberryBar.getValue(), inventoryItems.get("SNA001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCheetos")) {
			CartItem ci = new CartItem("SNA002", inventoryItems.get("SNA002").getProductName(),
					(Integer) spinCheetos.getValue(), inventoryItems.get("SNA002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnChips")) {
			CartItem ci = new CartItem("SNA003", inventoryItems.get("SNA003").getProductName(),
					(Integer) spinChips.getValue(), inventoryItems.get("SNA003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnChocolate")) {
			CartItem ci = new CartItem("SNA004", inventoryItems.get("SNA004").getProductName(),
					(Integer) spinChocolate.getValue(), inventoryItems.get("SNA004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnOnionDip")) {
			CartItem ci = new CartItem("SNA005", inventoryItems.get("SNA005").getProductName(),
					(Integer) spinOnionDip.getValue(), inventoryItems.get("SNA005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPopcorn")) {
			CartItem ci = new CartItem("SNA006", inventoryItems.get("SNA006").getProductName(),
					(Integer) spinPopcorn.getValue(), inventoryItems.get("SNA006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPringles")) {
			CartItem ci = new CartItem("SNA007", inventoryItems.get("SNA007").getProductName(),
					(Integer) spinPringles.getValue(), inventoryItems.get("SNA007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnTostitos")) {
			CartItem ci = new CartItem("SNA008", inventoryItems.get("SNA008").getProductName(),
					(Integer) spinTostitos.getValue(), inventoryItems.get("SNA008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnTrail")) {
			CartItem ci = new CartItem("SNA009", inventoryItems.get("SNA009").getProductName(),
					(Integer) spinTrail.getValue(), inventoryItems.get("SNA009").getPrice());

			cart.addProduct(ci);

		}

	}

	// Traverse back to the Catalog Page
	@FXML
	private void backToCatalog(ActionEvent event) {

		ScreenController.goToCatalogPage(event);
	}

	// Go to the Order Summary Page
	@FXML
	private void goToCart(ActionEvent event) {

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
