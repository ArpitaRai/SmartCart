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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DatabaseConnector;

public class FruitsController extends ProductBaseController {

	@FXML
	Label lblApplePrice;
	@FXML
	Label lblAvocadoPrice;
	@FXML
	Label lblBananaPrice;
	@FXML
	Label lblBlueberriesPrice;
	@FXML
	Label lblGuavaPrice;
	@FXML
	Label lblOrangePrice;
	@FXML
	Label lblPlumPrice;
	@FXML
	Label lblStrawberriesPrice;
	@FXML
	Label lblWatermelonPrice;
	
	@FXML
	Spinner<Integer> spinApple;
	@FXML
	Spinner<Integer> spinAvocado;
	@FXML
	Spinner<Integer> spinBanana;
	@FXML
	Spinner<Integer> spinBlueberries;
	@FXML
	Spinner<Integer> spinGuava;
	@FXML
	Spinner<Integer> spinOrange;
	@FXML
	Spinner<Integer> spinPlum;
	@FXML
	Spinner<Integer> spinStrawberries;
	@FXML
	Spinner<Integer> spinWatermelon;

	@FXML
	Button btnApple;
	@FXML
	Button btnAvocado;
	@FXML
	Button btnBanana;
	@FXML
	Button btnBlueberries;
	@FXML
	Button btnGuava;
	@FXML
	Button btnOrange;
	@FXML
	Button btnPlum;
	@FXML
	Button btnStrawberries;
	@FXML
	Button btnWatermelon;

	@Override
	public void initialize() {

//    	Fetch the Fruit Products information from the Fruits catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Fruits");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblApplePrice.setText("$" + inventoryItems.get("FRU001").getPrice() + "/lb");
		lblAvocadoPrice.setText("$" + inventoryItems.get("FRU002").getPrice() + "/lb");
		lblBananaPrice.setText("$" + inventoryItems.get("FRU003").getPrice() + "/lb");
		lblBlueberriesPrice.setText("$" + inventoryItems.get("FRU004").getPrice() + "/lb");
		lblGuavaPrice.setText("$" + inventoryItems.get("FRU005").getPrice() + "/lb");
		lblOrangePrice.setText("$" + inventoryItems.get("FRU006").getPrice() + "/ct");
		lblPlumPrice.setText("$" + inventoryItems.get("FRU007").getPrice() + "/lb");
		lblStrawberriesPrice.setText("$" + inventoryItems.get("FRU008").getPrice() + "/lb");
		lblWatermelonPrice.setText("$" + inventoryItems.get("FRU009").getPrice() + "/ct");

		spinApple.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU001").getQuantity(), 0));

		spinAvocado.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU002").getQuantity(), 0));

		spinBanana.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU003").getQuantity(), 0));

		spinBlueberries.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU004").getQuantity(), 0));

		spinGuava.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU005").getQuantity(), 0));

		spinOrange.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU006").getQuantity(), 0));

		spinPlum.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU007").getQuantity(), 0));

		spinStrawberries.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU008").getQuantity(), 0));

		spinWatermelon.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("FRU009").getQuantity(), 0));
		
		
		if(inventoryItems.get("FRU001").getQuantity() == 0) {
			setOutOfStockField(lblApplePrice, spinApple, btnApple);
		}
		if(inventoryItems.get("FRU002").getQuantity() == 0) {
			setOutOfStockField(lblAvocadoPrice, spinAvocado, btnAvocado);
		}
		if(inventoryItems.get("FRU003").getQuantity() == 0) {
			setOutOfStockField(lblBananaPrice, spinBanana, btnBanana);
		}
		if(inventoryItems.get("FRU004").getQuantity() == 0) {
			setOutOfStockField(lblBlueberriesPrice, spinBlueberries, btnBlueberries);
		}
		if(inventoryItems.get("FRU005").getQuantity() == 0) {
			setOutOfStockField(lblGuavaPrice, spinGuava, btnGuava);
		}
		if(inventoryItems.get("FRU006").getQuantity() == 0) {
			setOutOfStockField(lblOrangePrice,spinOrange, btnOrange);
		}
		if(inventoryItems.get("FRU007").getQuantity() == 0) {
			setOutOfStockField(lblPlumPrice, spinPlum, btnPlum);
		}
		if(inventoryItems.get("FRU008").getQuantity() == 0) {
			setOutOfStockField(lblStrawberriesPrice, spinStrawberries, btnStrawberries);
		}
		if(inventoryItems.get("FRU009").getQuantity() == 0) {
			setOutOfStockField(lblWatermelonPrice, spinWatermelon, btnWatermelon);
		}
		
		
	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnApple")) {
			CartItem ci = new CartItem("FRU001", inventoryItems.get("FRU001").getProductName(), 
					(Integer) spinApple.getValue(), inventoryItems.get("FRU001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnAvocado")) {
			CartItem ci = new CartItem("FRU002", inventoryItems.get("FRU002").getProductName(), 
					(Integer) spinAvocado.getValue(), inventoryItems.get("FRU002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnBanana")) {
			CartItem ci = new CartItem("FRU003", inventoryItems.get("FRU003").getProductName(), 
					(Integer) spinBanana.getValue(), inventoryItems.get("FRU003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnBlueberries")) {
			CartItem ci = new CartItem("FRU004", inventoryItems.get("FRU004").getProductName(), 
					(Integer) spinBlueberries.getValue(), inventoryItems.get("FRU004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnGuava")) {
			CartItem ci = new CartItem("FRU005", inventoryItems.get("FRU005").getProductName(), 
					(Integer) spinGuava.getValue(), inventoryItems.get("FRU005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnOrange")) {
			CartItem ci = new CartItem("FRU006", inventoryItems.get("FRU006").getProductName(), 
					(Integer) spinOrange.getValue(), inventoryItems.get("FRU006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPlum")) {
			CartItem ci = new CartItem("FRU007", inventoryItems.get("FRU007").getProductName(), 
					(Integer) spinPlum.getValue(), inventoryItems.get("FRU007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnStrawberries")) {
			CartItem ci = new CartItem("FRU008", inventoryItems.get("FRU008").getProductName(), 
					(Integer) spinStrawberries.getValue(), inventoryItems.get("FRU008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnWatermelon")) {
			CartItem ci = new CartItem("FRU009", inventoryItems.get("FRU009").getProductName(), 
					(Integer) spinWatermelon.getValue(), inventoryItems.get("FRU009").getPrice());

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
	private void setOutOfStockField(Label errorLabel, Spinner spinner, Button bt){
		errorLabel.setText("Out of Stock");
		errorLabel.setTextFill(Color.RED);
		spinner.setDisable(true);
		bt.setDisable(true);
	}

}
