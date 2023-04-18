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

public class BakeryController extends ProductBaseController {

	@FXML Label lblWholeWheatBreadPrice;
	@FXML Label lblCheeseCakePrice;
	@FXML Label lblChocolateChipMuffinPrice;
	@FXML Label lblCookiePrice;
	@FXML Label lblTiramisuPrice;
	@FXML Label lblHoneyBreadPrice;
	@FXML Label lblStrawberryShortcakePrice;
	@FXML Label lblDonutPrice;
	@FXML Label lblCrossaintPrice;

	@FXML Spinner<Integer> spinWholeWheatBread;
	@FXML Spinner<Integer> spinCheeseCake;
	@FXML Spinner<Integer> spinChocolateChipMuffin;
	@FXML Spinner<Integer> spinCookie;
	@FXML Spinner<Integer>spinTiramisu;
	@FXML Spinner<Integer> spinHoneyBread;
	@FXML Spinner<Integer> spinStrawberryShortcake;
	@FXML Spinner<Integer> spinDonut;
	@FXML Spinner<Integer> spinCrossaint;

	@FXML Button btnWholeWheatBread;
	@FXML Button btnCheeseCake;
	@FXML Button btnChocolateChipMuffin;
	@FXML Button btnCookie;
	@FXML Button btnTiramisu;
	@FXML Button btnHoneyBread;
	@FXML Button btnStrawberryShortcake;
	@FXML Button btnDonut;
	@FXML Button btnCrossaint;

	@Override
	public void initialize() {
		// Fetch the Fruit Products information from the Fruits catalog in the Database
		try {
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Dairy");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		lblWholeWheatBreadPrice.setText("$" + inventoryItems.get("BAK001").getPrice());
		lblCheeseCakePrice.setText("$" + inventoryItems.get("BAK002").getPrice());
		lblChocolateChipMuffinPrice.setText("$" + inventoryItems.get("BAK003").getPrice());
		lblCookiePrice.setText("$" + inventoryItems.get("BAK004").getPrice());
		lblTiramisuPrice.setText("$" + inventoryItems.get("BAK005").getPrice());
		lblHoneyBreadPrice.setText("$" + inventoryItems.get("BAK006").getPrice());
		lblStrawberryShortcakePrice.setText("$" + inventoryItems.get("BAK007").getPrice());
		lblDonutPrice.setText("$" + inventoryItems.get("BAK008").getPrice());
		lblCrossaintPrice.setText("$" + inventoryItems.get("BAK009").getPrice());

		spinWholeWheatBread.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK001").getQty(), 0));

		spinCheeseCake.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK002").getQty(), 0));

		spinChocolateChipMuffin.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK003").getQty(), 0));

		spinCookie.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK004").getQty(), 0));

		spinTiramisu.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK005").getQty(), 0));

		spinHoneyBread.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK006").getQty(), 0));

		spinStrawberryShortcake.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK007").getQty(), 0));

		spinDonut.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK008").getQty(), 0));

		spinCrossaint.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("BAK009").getQty(), 0));
		
		if(inventoryItems.get("BAK001").getQty() == 0) {
			setOutOfStockField(lblWholeWheatBreadPrice, spinWholeWheatBread, btnWholeWheatBread);
		}
		if(inventoryItems.get("BAK002").getQty() == 0) {
			setOutOfStockField(lblCheeseCakePrice, spinCheeseCake, btnCheeseCake);
		}
		if(inventoryItems.get("BAK003").getQty() == 0) {
			setOutOfStockField(lblChocolateChipMuffinPrice, spinChocolateChipMuffin, btnChocolateChipMuffin);
		}
		if(inventoryItems.get("BAK004").getQty() == 0) {
			setOutOfStockField(lblCookiePrice, spinCookie, btnCookie);
		}
		if(inventoryItems.get("BAK005").getQty() == 0) {
			setOutOfStockField(lblTiramisuPrice, spinTiramisu, btnTiramisu);
		}
		if(inventoryItems.get("BAK006").getQty() == 0) {
			setOutOfStockField(lblHoneyBreadPrice, spinHoneyBread, btnHoneyBread);
		}
		if(inventoryItems.get("BAK007").getQty() == 0) {
			setOutOfStockField(lblStrawberryShortcakePrice, spinStrawberryShortcake, btnStrawberryShortcake);
		}
		if(inventoryItems.get("BAK008").getQty() == 0) {
			setOutOfStockField(lblDonutPrice, spinDonut, btnDonut);
		}
		if(inventoryItems.get("BAK009").getQty() == 0) {
			setOutOfStockField(lblCrossaintPrice, spinCrossaint, btnCrossaint);
		}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnWholeWheatBread")) {
			CartItem ci = new CartItem("BAK001", "Whole Wheat Bread", (Integer) spinWholeWheatBread.getValue(),
					inventoryItems.get("BAK001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCheeseCake")) {
			CartItem ci = new CartItem("BAK002", "CheeseCake", (Integer) spinCheeseCake.getValue(),
					inventoryItems.get("BAK002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnChocolateChipMuffin")) {
			CartItem ci = new CartItem("BAK003", "Chocolate Chip Muffin", (Integer) spinChocolateChipMuffin.getValue(),
					inventoryItems.get("BAK003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCookie")) {
			CartItem ci = new CartItem("BAK004", "Cookie", (Integer) spinCookie.getValue(),
					inventoryItems.get("BAK004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnTiramisu")) {
			CartItem ci = new CartItem("BAK005", "Tiramisu", (Integer)spinTiramisu.getValue(),
					inventoryItems.get("BAK005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnHoneyBread")) {
			CartItem ci = new CartItem("BAK006", "Honey Bread", (Integer) spinHoneyBread.getValue(),
					inventoryItems.get("BAK006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnStrawberryShortcake")) {
			CartItem ci = new CartItem("BAK007", "Strawberry Shortcake", (Integer) spinStrawberryShortcake.getValue(),
					inventoryItems.get("BAK007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnDonut")) {
			CartItem ci = new CartItem("BAK008", "Donut", (Integer) spinDonut.getValue(),
					inventoryItems.get("BAK008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCrossaint")) {
			CartItem ci = new CartItem("BAK009", "Crossaint", (Integer) spinCrossaint.getValue(),
					inventoryItems.get("BAK009").getPrice());

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
