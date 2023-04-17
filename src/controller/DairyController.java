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

public class DairyController extends ProductBaseController {

	@FXML Label lblMilkPrice;
	@FXML Label lblButterPrice;
	@FXML Label lblHeavyCreamPrice;
	@FXML Label lblIceCreamPrice;
	@FXML Label lblChocolateMilkPrice;
	@FXML Label lblCottageCheesePrice;
	@FXML Label lblStringCheesePrice;
	@FXML Label lblMozzarellaCheesePrice;
	@FXML Label lblYogurtPrice;

	@FXML Spinner<Integer> spinMilk;
	@FXML Spinner<Integer> spinButter;
	@FXML Spinner<Integer> spinHeavyCream;
	@FXML Spinner<Integer> spinIceCream;
	@FXML Spinner<Integer>spinChocolateMilk;
	@FXML Spinner<Integer> spinCottageCheese;
	@FXML Spinner<Integer> spinStringCheese;
	@FXML Spinner<Integer> spinMozzarellaCheese;
	@FXML Spinner<Integer> spinYogurt;

	@FXML Button btnMilk;
	@FXML Button btnButter;
	@FXML Button btnHeavyCream;
	@FXML Button btnIceCream;
	@FXML Button btnChocolateMilk;
	@FXML Button btnCottageCheese;
	@FXML Button btnStringCheese;
	@FXML Button btnMozzarellaCheese;
	@FXML Button btnYogurt;

	@Override
	public void initialize() {
		// Fetch the Fruit Products information from the Fruits catalog in the Database
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

		lblMilkPrice.setText("$" + inventoryItems.get("DAI001").getPrice());
		lblButterPrice.setText("$" + inventoryItems.get("DAI002").getPrice());
		lblHeavyCreamPrice.setText("$" + inventoryItems.get("DAI003").getPrice());
		lblIceCreamPrice.setText("$" + inventoryItems.get("DAI004").getPrice());
		lblChocolateMilkPrice.setText("$" + inventoryItems.get("DAI005").getPrice());
		lblCottageCheesePrice.setText("$" + inventoryItems.get("DAI006").getPrice());
		lblStringCheesePrice.setText("$" + inventoryItems.get("DAI007").getPrice());
		lblMozzarellaCheesePrice.setText("$" + inventoryItems.get("DAI008").getPrice());
		lblYogurtPrice.setText("$" + inventoryItems.get("DAI009").getPrice());

		spinMilk.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI001").getQty(), 0));

		spinButter.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI002").getQty(), 0));

		spinHeavyCream.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI003").getQty(), 0));

		spinIceCream.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI004").getQty(), 0));

		spinChocolateMilk.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI005").getQty(), 0));

		spinCottageCheese.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI006").getQty(), 0));

		spinStringCheese.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI007").getQty(), 0));

		spinMozzarellaCheese.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI008").getQty(), 0));

		spinYogurt.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("DAI009").getQty(), 0));
		
		if(inventoryItems.get("DAI001").getQty() == 0) {
			setOutOfStockField(lblMilkPrice, spinMilk, btnMilk);
		}
		if(inventoryItems.get("DAI002").getQty() == 0) {
			setOutOfStockField(lblButterPrice, spinButter, btnButter);
		}
		if(inventoryItems.get("DAI003").getQty() == 0) {
			setOutOfStockField(lblHeavyCreamPrice, spinHeavyCream, btnHeavyCream);
		}
		if(inventoryItems.get("DAI004").getQty() == 0) {
			setOutOfStockField(lblIceCreamPrice, spinIceCream, btnIceCream);
		}
		if(inventoryItems.get("DAI005").getQty() == 0) {
			setOutOfStockField(lblChocolateMilkPrice, spinChocolateMilk, btnChocolateMilk);
		}
		if(inventoryItems.get("DAI006").getQty() == 0) {
			setOutOfStockField(lblCottageCheesePrice, spinCottageCheese, btnCottageCheese);
		}
		if(inventoryItems.get("DAI007").getQty() == 0) {
			setOutOfStockField(lblStringCheesePrice, spinStringCheese, btnStringCheese);
		}
		if(inventoryItems.get("DAI008").getQty() == 0) {
			setOutOfStockField(lblMozzarellaCheesePrice, spinMozzarellaCheese, btnMozzarellaCheese);
		}
		if(inventoryItems.get("DAI009").getQty() == 0) {
			setOutOfStockField(lblYogurtPrice, spinYogurt, btnYogurt);
		}

	}

	// Add selected products to the cart list
	@FXML
	private void addToCart(javafx.event.ActionEvent event) {

		if (((Button) event.getTarget()).getId().toString().equals("btnMilk")) {
			CartItem ci = new CartItem("DAI001", "Milk", (Integer) spinMilk.getValue(),
					inventoryItems.get("DAI001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnButter")) {
			CartItem ci = new CartItem("DAI002", "Butter", (Integer) spinButter.getValue(),
					inventoryItems.get("DAI002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnHeavyCream")) {
			CartItem ci = new CartItem("DAI003", "Heavy Cream", (Integer) spinHeavyCream.getValue(),
					inventoryItems.get("DAI003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnIceCream")) {
			CartItem ci = new CartItem("DAI004", "Ice Cream", (Integer) spinIceCream.getValue(),
					inventoryItems.get("DAI004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnChocolateMilk")) {
			CartItem ci = new CartItem("DAI005", "Chocolate Milk", (Integer)spinChocolateMilk.getValue(),
					inventoryItems.get("DAI005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnSourCream")) {
			CartItem ci = new CartItem("DAI006", "Sour Cream", (Integer) spinCottageCheese.getValue(),
					inventoryItems.get("DAI006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnStringCheese")) {
			CartItem ci = new CartItem("DAI007", "String Cheese", (Integer) spinStringCheese.getValue(),
					inventoryItems.get("DAI007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnMozzarellaCheese")) {
			CartItem ci = new CartItem("DAI008", "Mozzarella Cheese", (Integer) spinMozzarellaCheese.getValue(),
					inventoryItems.get("DAI008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnYogurt")) {
			CartItem ci = new CartItem("DAI009", "Yogurt", (Integer) spinYogurt.getValue(),
					inventoryItems.get("DAI009").getPrice());

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
