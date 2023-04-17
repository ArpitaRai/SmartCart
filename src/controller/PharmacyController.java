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

public class PharmacyController extends ProductBaseController {

    @FXML
    private Spinner<Integer> spinBandAid;
    @FXML
    private Spinner<Integer> spinAdvil;
    @FXML
    private Spinner<Integer> spinParacetamol;
    @FXML
    private Spinner<Integer> spinVicks;
    @FXML
    private Spinner<Integer> spinAntacid;
    @FXML
    private Spinner<Integer> spinAntibiotic;
    @FXML
    private Spinner<Integer> spinVaseline;
    @FXML
    private Spinner<Integer> spinBenadryl;
    @FXML
    private Spinner<Integer> spinNasalSpray;

	
	@FXML
    private Button btnBandAid;
    @FXML
    private Label lblBandAidPrice;
    @FXML
    private Button btnAdvil;
    @FXML
    private Label lblAdvilPrice;
    @FXML
    private Button btnParacetamol;
    @FXML
    private Label lblParacetamolPrice;
    @FXML
    private Button btnVicks;
    @FXML
    private Label lblVicksPrice;
    @FXML
    private Button btnAntacid;
    @FXML
    private Label lblAntacidPrice;
    @FXML
    private Button btnAntibiotic;
    @FXML
    private Label lblAntibioticPrice;
    @FXML
    private Button btnVaseline;
    @FXML
    private Label lblVaselinePrice;
   @FXML
    private Button btnBenadryl;
    @FXML
    private Label lblBenadrylPrice;
    @FXML
    private Button btnNasalSpray;
    @FXML
    private Label lblNasalSprayPrice;





	public void initialize() {
		try {	
		try {
			ResultSet rs = DatabaseConnector.getItemsFromCatalog("Pharmacy");
			System.out.println(rs);
			while (rs.next()) {
				inventoryItems.put(rs.getString(1),
						new Product(rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDouble(3), rs.getString(5)));
			}
			DatabaseConnector.closeStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblAdvilPrice.setText("$" + inventoryItems.get("PHA001").getPrice());
		lblAntacidPrice.setText("$" + inventoryItems.get("PHA002").getPrice());
		lblBandAidPrice.setText("$" + inventoryItems.get("PHA003").getPrice());
		lblVicksPrice.setText("$" + inventoryItems.get("PHA004").getPrice());
		lblParacetamolPrice.setText("$" + inventoryItems.get("PHA005").getPrice());
		lblAntibioticPrice.setText("$" + inventoryItems.get("PHA006").getPrice());
		lblVaselinePrice.setText("$" + inventoryItems.get("PHA007").getPrice());
		lblNasalSprayPrice.setText("$" + inventoryItems.get("PHA008").getPrice());
		lblBenadrylPrice.setText("$" + inventoryItems.get("PHA009").getPrice());

		spinAdvil.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA001").getQty(), 0));

		spinAntacid.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA002").getQty(), 0));

		spinBandAid.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA003").getQty(), 0));

		spinVicks.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA004").getQty(), 0));

		spinParacetamol.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA005").getQty(), 0));

		spinAntibiotic.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA006").getQty(), 0));

		spinVaseline.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA007").getQty(), 0));

		spinNasalSpray.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA008").getQty(), 0));

		spinBenadryl.setValueFactory(
				new SpinnerValueFactory.IntegerSpinnerValueFactory(0, inventoryItems.get("PHA009").getQty(), 0));
		
		if(inventoryItems.get("PHA001").getQty() == 0) {
			setOutOfStockField(lblAdvilPrice, spinAdvil, btnAdvil);
		}
		if(inventoryItems.get("PHA002").getQty() == 0) {
			setOutOfStockField(lblAntacidPrice, spinAntacid, btnAntacid);
		}
		if(inventoryItems.get("PHA003").getQty() == 0) {
			setOutOfStockField(lblBandAidPrice, spinBandAid, btnBandAid);
		}
		if(inventoryItems.get("PHA004").getQty() == 0) {
			setOutOfStockField(lblVicksPrice, spinVicks, btnVicks);
		}
		if(inventoryItems.get("PHA005").getQty() == 0) {
			setOutOfStockField(lblParacetamolPrice, spinParacetamol, btnParacetamol);
		}
		if(inventoryItems.get("PHA006").getQty() == 0) {
			setOutOfStockField(lblAntibioticPrice, spinAntibiotic, btnAntibiotic);
		}
		if(inventoryItems.get("PHA007").getQty() == 0) {
			setOutOfStockField(lblVaselinePrice, spinVaseline, btnVaseline);
		}
		if(inventoryItems.get("PHA008").getQty() == 0) {
			setOutOfStockField(lblNasalSprayPrice, spinNasalSpray, btnNasalSpray);
		}
		if(inventoryItems.get("PHA009").getQty() == 0) {
			setOutOfStockField(lblBenadrylPrice, spinBenadryl, btnBenadryl);
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

		if (((Button) event.getTarget()).getId().toString().equals("btnAdvil")) {
			CartItem ci = new CartItem("PHA001", "Chicken", (Integer) spinAdvil.getValue(),
					inventoryItems.get("PHA001").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnAntacid")) {
			CartItem ci = new CartItem("PHA002", "Fish", (Integer) spinAntacid.getValue(),
					inventoryItems.get("PHA002").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnBandAid")) {
			CartItem ci = new CartItem("PHA003","Beef", (Integer) spinBandAid.getValue(),
					inventoryItems.get("PHA003").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnCrab")) {
			CartItem ci = new CartItem("PHA004", "Crab", (Integer) spinVicks.getValue(),
					inventoryItems.get("PHA004").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnParacetamol")) {
			CartItem ci = new CartItem("PHA005", "Clam", (Integer)spinParacetamol.getValue(),
					inventoryItems.get("PHA005").getPrice());

			cart.addProduct(ci);
		}

		if (((Button) event.getTarget()).getId().toString().equals("btnAntibiotic")) {
			CartItem ci = new CartItem("PHA006", "Lamb", (Integer) spinAntibiotic.getValue(),
					inventoryItems.get("PHA006").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnVaseline")) {
			CartItem ci = new CartItem("PHA007", "Pork", (Integer) spinVaseline.getValue(),
					inventoryItems.get("PHA007").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnNasalSpray")) {
			CartItem ci = new CartItem("PHA008", "Turkey", (Integer) spinNasalSpray.getValue(),
					inventoryItems.get("PHA008").getPrice());

			cart.addProduct(ci);

		}

		if (((Button) event.getTarget()).getId().toString().equals("btnBenadryl")) {
			CartItem ci = new CartItem("PHA009", "Prawns", (Integer) spinBenadryl.getValue(),
					inventoryItems.get("PHA009").getPrice());

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
