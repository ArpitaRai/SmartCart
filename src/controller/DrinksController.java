package controller;

import java.sql.ResultSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.paint.Color;
import model.CartItem;
import model.DatabaseConnector;
import model.Product;

public class DrinksController extends ProductBaseController {
	@FXML Label lblWaterPrice;
	@FXML Label lblDietCokePrice;
	@FXML Label lblSpritePrice;
	@FXML Label lblFruitPunchPrice;
	@FXML Label lblMountainDewPrice;
	@FXML Label lblOrangeJuicePrice;
	@FXML Label lblPepsiPrice;
	@FXML Label lblRedBullPrice;
	@FXML Label lblDunkinCoffeePrice;

	@FXML Spinner<Integer> spinWater;
	@FXML Spinner<Integer> spinDietCoke;
	@FXML Spinner<Integer> spinSprite;
	@FXML Spinner<Integer> spinFruitPunch;
	@FXML Spinner<Integer> spinMountainDew;
	@FXML Spinner<Integer> spinOrangeJuice;
	@FXML Spinner<Integer> spinPepsi;
	@FXML Spinner<Integer> spinRedBull;
	@FXML Spinner<Integer> spinDunkinCoffee;

	@FXML Button btnWater;
	@FXML Button btnDietCoke;
	@FXML Button btnSprite;
	@FXML Button btnFruitPunch;
	@FXML Button btnMountainDew;
	@FXML Button btnOrangeJuice;
	@FXML Button btnPepsi;
	@FXML Button btnRedBull;
	@FXML Button btnDunkinCoffee;

	public void initialize() {
//		Fetch the Beverages Products information from the Beverages catalog in the Database
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Drinks");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblWaterPrice.setText("$" + inventoryItems.get("BEV001").getPrice());
		lblDietCokePrice.setText("$" + inventoryItems.get("BEV002").getPrice());
		lblSpritePrice.setText("$" + inventoryItems.get("BEV003").getPrice());
		lblFruitPunchPrice.setText("$" + inventoryItems.get("BEV004").getPrice());
		lblMountainDewPrice.setText("$" + inventoryItems.get("BEV005").getPrice());
		lblOrangeJuicePrice.setText("$" + inventoryItems.get("BEV006").getPrice());
		lblPepsiPrice.setText("$" + inventoryItems.get("BEV007").getPrice());
		lblRedBullPrice.setText("$" + inventoryItems.get("BEV008").getPrice());
		lblDunkinCoffeePrice.setText("$" + inventoryItems.get("BEV009").getPrice());

		spinWater.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV001").getQty(), 0));

		spinDietCoke.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV002").getQty(), 0));

		spinSprite.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV003").getQty(), 0));

		spinFruitPunch.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV004").getQty(), 0));

		spinMountainDew.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV005").getQty(), 0));

		spinOrangeJuice.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV006").getQty(), 0));

		spinPepsi.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV007").getQty(), 0));

		spinRedBull.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV008").getQty(), 0));

		spinDunkinCoffee.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BEV009").getQty(), 0));
		
		if(inventoryItems.get("BEV001").getQty() == 0) {
			setOutOfStockField(lblWaterPrice, spinWater, btnWater);
		}
		if(inventoryItems.get("BEV002").getQty() == 0) {
			setOutOfStockField(lblDietCokePrice, spinDietCoke, btnDietCoke);
		}
		if(inventoryItems.get("BEV003").getQty() == 0) {
			setOutOfStockField(lblSpritePrice, spinSprite, btnSprite);
		}
		if(inventoryItems.get("BEV004").getQty() == 0) {
			setOutOfStockField(lblFruitPunchPrice, spinFruitPunch, btnFruitPunch);
		}
		if(inventoryItems.get("BEV005").getQty() == 0) {
			setOutOfStockField(lblMountainDewPrice, spinMountainDew, btnMountainDew);
		}
		if(inventoryItems.get("BEV006").getQty() == 0) {
			setOutOfStockField(lblOrangeJuicePrice, spinOrangeJuice, btnOrangeJuice);
		}
		if(inventoryItems.get("BEV007").getQty() == 0) {
			setOutOfStockField(lblPepsiPrice, spinPepsi, btnPepsi);
		}
		if(inventoryItems.get("BEV008").getQty() == 0) {
			setOutOfStockField(lblRedBullPrice, spinRedBull, btnRedBull);
		}
		if(inventoryItems.get("BEV009").getQty() == 0) {
			setOutOfStockField(lblDunkinCoffeePrice, spinDunkinCoffee, btnDunkinCoffee);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnWater")) {
			CartItem ci = new CartItem("BEV001", "Water", (Integer) spinWater.getValue(),
					inventoryItems.get("BEV001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnDietCoke")) {
			CartItem ci = new CartItem("BEV002", "Diet Coke", (Integer) spinDietCoke.getValue(),
					inventoryItems.get("BEV002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnSprite")) {
			CartItem ci = new CartItem("BEV003", "KoolAid", (Integer) spinSprite.getValue(),
					inventoryItems.get("BEV003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnFruitPunch")) {
			CartItem ci = new CartItem("BEV004", "Fruit Punch", (Integer) spinFruitPunch.getValue(),
					inventoryItems.get("BEV004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnMountainDew")) {
			CartItem ci = new CartItem("BEV005", "Gatorade", (Integer)spinMountainDew.getValue(),
					inventoryItems.get("BEV005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnOrangeJuice")) {
			CartItem ci = new CartItem("BEV006", "Orange Juice", (Integer) spinOrangeJuice.getValue(),
					inventoryItems.get("BEV006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPepsi")) {
			CartItem ci = new CartItem("BEV007", "Pepsi", (Integer) spinPepsi.getValue(),
					inventoryItems.get("BEV007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnRedBull")) {
			CartItem ci = new CartItem("BEV008", "Red Bull", (Integer) spinRedBull.getValue(),
					inventoryItems.get("BEV008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnDunkinCoffee")) {
			CartItem ci = new CartItem("BEV009", "Seltzer", (Integer) spinDunkinCoffee.getValue(),
					inventoryItems.get("BEV009").getPrice());

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
		logOut();
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
