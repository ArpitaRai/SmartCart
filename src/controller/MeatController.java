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

public class MeatController extends ProductBaseController {

    @FXML
    private Spinner<Integer> spinBeef;
    @FXML
    private Spinner<Integer> spinChicken;
    @FXML
    private Spinner<Integer> spinClam;
    @FXML
    private Spinner<Integer> spinCrab;
    @FXML
    private Spinner<Integer> spinFish;
    @FXML
    private Spinner<Integer> spinLamb;
    @FXML
    private Spinner<Integer> spinPork;
    @FXML
    private Spinner<Integer> spinPrawns;
    @FXML
    private Spinner<Integer> spinTurkey;

	
	@FXML
    private Button btnBeef;
    @FXML
    private Label lblBeefPrice;
    @FXML
    private Button btnChicken;
    @FXML
    private Label lblChickenPrice;
    @FXML
    private Button btnClam;
    @FXML
    private Label lblClamPrice;
    @FXML
    private Button btnCrab;
    @FXML
    private Label lblCrabPrice;
    @FXML
    private Button btnFish;
    @FXML
    private Label lblFishPrice;
    @FXML
    private Button btnLamb;
    @FXML
    private Label lblLambPrice;
    @FXML
    private Button btnPork;
    @FXML
    private Label lblPorkPrice;
   @FXML
    private Button btnPrawns;
    @FXML
    private Label lblPrawnsPrice;
    @FXML
    private Button btnTurkey;
    @FXML
    private Label lblTurkeyPrice;





	public void initialize() {

//   	Fetch the Meat Products information from the Meat catalog in the Database	
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Meat");
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblChickenPrice.setText("$" + inventoryItems.get("MEA001").getPrice());
		lblFishPrice.setText("$" + inventoryItems.get("MEA002").getPrice());
		lblBeefPrice.setText("$" + inventoryItems.get("MEA003").getPrice());
		lblCrabPrice.setText("$" + inventoryItems.get("MEA004").getPrice());
		lblClamPrice.setText("$" + inventoryItems.get("MEA005").getPrice());
		lblLambPrice.setText("$" + inventoryItems.get("MEA006").getPrice());
		lblPorkPrice.setText("$" + inventoryItems.get("MEA007").getPrice());
		lblTurkeyPrice.setText("$" + inventoryItems.get("MEA008").getPrice());
		lblPrawnsPrice.setText("$" + inventoryItems.get("MEA009").getPrice());

		spinChicken.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA001").getQuantity(), 0));

		spinFish.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA002").getQuantity(), 0));

		spinBeef.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA003").getQuantity(), 0));

		spinCrab.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA004").getQuantity(), 0));

		spinClam.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA005").getQuantity(), 0));

		spinLamb.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA006").getQuantity(), 0));

		spinPork.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA007").getQuantity(), 0));

		spinTurkey.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA008").getQuantity(), 0));

		spinPrawns.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("MEA009").getQuantity(), 0));
		
		if(inventoryItems.get("MEA001").getQuantity() == 0) {
			setOutOfStockField(lblChickenPrice, spinChicken, btnChicken);
		}
		if(inventoryItems.get("MEA002").getQuantity() == 0) {
			setOutOfStockField(lblFishPrice, spinFish, btnFish);
		}
		if(inventoryItems.get("MEA003").getQuantity() == 0) {
			setOutOfStockField(lblBeefPrice, spinBeef, btnBeef);
		}
		if(inventoryItems.get("MEA004").getQuantity() == 0) {
			setOutOfStockField(lblCrabPrice, spinCrab, btnCrab);
		}
		if(inventoryItems.get("MEA005").getQuantity() == 0) {
			setOutOfStockField(lblClamPrice, spinClam, btnClam);
		}
		if(inventoryItems.get("MEA006").getQuantity() == 0) {
			setOutOfStockField(lblLambPrice, spinLamb, btnLamb);
		}
		if(inventoryItems.get("MEA007").getQuantity() == 0) {
			setOutOfStockField(lblPorkPrice, spinPork, btnPork);
		}
		if(inventoryItems.get("MEA008").getQuantity() == 0) {
			setOutOfStockField(lblTurkeyPrice, spinTurkey, btnTurkey);
		}
		if(inventoryItems.get("MEA009").getQuantity() == 0) {
			setOutOfStockField(lblPrawnsPrice, spinPrawns, btnPrawns);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnChicken")) {
			CartItem ci = new CartItem("MEA001", "Chicken", (Integer) spinChicken.getValue(),
					inventoryItems.get("MEA001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnFish")) {
			CartItem ci = new CartItem("MEA002", "Fish", (Integer) spinFish.getValue(),
					inventoryItems.get("MEA002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnBeef")) {
			CartItem ci = new CartItem("MEA003","Beef", (Integer) spinBeef.getValue(),
					inventoryItems.get("MEA003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCrab")) {
			CartItem ci = new CartItem("MEA004", "Crab", (Integer) spinCrab.getValue(),
					inventoryItems.get("MEA004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnClam")) {
			CartItem ci = new CartItem("MEA005", "Clam", (Integer)spinClam.getValue(),
					inventoryItems.get("MEA005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnLamb")) {
			CartItem ci = new CartItem("MEA006", "Lamb", (Integer) spinLamb.getValue(),
					inventoryItems.get("MEA006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPork")) {
			CartItem ci = new CartItem("MEA007", "Pork", (Integer) spinPork.getValue(),
					inventoryItems.get("MEA007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnTurkey")) {
			CartItem ci = new CartItem("MEA008", "Turkey", (Integer) spinTurkey.getValue(),
					inventoryItems.get("MEA008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnPrawns")) {
			CartItem ci = new CartItem("MEA009", "Prawns", (Integer) spinPrawns.getValue(),
					inventoryItems.get("MEA009").getPrice());

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
