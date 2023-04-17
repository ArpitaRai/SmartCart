
package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.CartItem;
import model.DatabaseConnector;
import model.Product;

public class PaymentSceneController extends ProductBaseController {

	private boolean codStatus = true;

	@FXML
	private Button confirmorder;

	@FXML
	private TextField addressField;
	
	@FXML
	private TextField nameField;

	@FXML
	private DatePicker dateField;

	@FXML
	private RadioButton cashondelivery;

	@FXML
	private RadioButton creditcard;

	@FXML
	private Label lbl;

	@FXML
	private AnchorPane myAnchorPane;

	@FXML
	private TextField cardnumber;

	@FXML
	private TextField cvvField;


	@FXML
	private Label errorAddress;

	@FXML
	private Label errorCardNumber;

	@FXML
	private ToggleGroup payment;

	@FXML
	void goToLogin(ActionEvent event) {
		logOff();
		ScreenController.goToLoginPage(event);
	}

	@FXML
	void goToCart(ActionEvent event) {
		ScreenController.goToCartPage(event);
	}

	@FXML
	void alterCardFields(ActionEvent event) {
		codStatus = true;
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	@FXML
	void alterCOD(ActionEvent event) {
		codStatus = false;
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	@Override
	public void initialize() {
		cardnumber.setDisable(codStatus);
		cvvField.setDisable(codStatus);
		nameField.setDisable(codStatus);
		dateField.setDisable(codStatus);
	}

	private String order_date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());

	@FXML
	public void handlebtnconfirmorder(ActionEvent event) {
		if (isAddressValid()) {
			errorAddress.setVisible(false);
			addressField.setStyle(null);
			if (codStatus) {
				proceedOrder(event);
			} else {
				String cardnum = cardnumber.getText();
				long number = Long.parseLong(cardnum);
				if (isValid(number)) {
					errorCardNumber.setVisible(false);
					cardnumber.setStyle(null);
					proceedOrder(event);
				} else {
					errorCardNumber.setVisible(true);
					cardnumber.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
					new animatefx.animation.Shake(cardnumber).play();
					Dialog<String> dialog = new Dialog<String>();
					dialog.setTitle("Invalid Card");
					ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
					dialog.setContentText("Card Number is invalid");
					dialog.getDialogPane().getButtonTypes().add(type);
					dialog.showAndWait();

				}
			}
		} else {
			errorAddress.setVisible(true);
			addressField.setStyle("-fx-border-color: red; -fx-border-width: 2px ;");
			new animatefx.animation.Shake(addressField).play();
			Dialog<String> dialog = new Dialog<String>();
			// Setting the title
			dialog.setTitle("Invalid Address");
			ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
			// Setting the content of the dialog
			dialog.setContentText("Address cannot be empty");
			// Adding buttons to the dialog pane
			dialog.getDialogPane().getButtonTypes().add(type);
			dialog.showAndWait();
		}

	}

	private void proceedOrder(ActionEvent event) {
		Dialog<String> dialog = new Dialog<String>();
		updateCartItems();
		// Setting the title
		dialog.setTitle("Order Confirmation");
		ButtonType type = new ButtonType("OK", ButtonData.OK_DONE);
		// Setting the content of the dialog
		dialog.setContentText("Order is successfully placed!");
		// Adding buttons to the dialog pane
		dialog.getDialogPane().getButtonTypes().add(type);
		dialog.showAndWait();
		logOff();
		ScreenController.goToCatalogPage(event);
	}

	private void updateCartItems() {
		try {
			List<CartItem> cartList = cart.getCartItems();
			Connection conn = DatabaseConnector.getInstance();
			for (CartItem item : cartList) {
				String key = item.getProductId();
				Product productInventory = inventoryItems.get(key);
				int netQuantity = productInventory.getQuantity() - item.getQuantity();
				saveOrderHistory(conn, item, productInventory);
				updateInventory(conn, item.getProductId(), netQuantity);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateInventory(Connection conn, String key, int quantity) {
		String query = "update product_list set productQuantity = ? where productId = ?";
		PreparedStatement preparedStmt;
		try {

			preparedStmt = conn.prepareStatement(query);
			preparedStmt.setInt(1, quantity);
			preparedStmt.setString(2, key);
			preparedStmt.executeUpdate();
			preparedStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void saveOrderHistory(Connection conn, CartItem item, Product product) {
		String query = "insert into user_order_history values (?,?,?,?,?)";
		PreparedStatement orderStmt;
		try {
			orderStmt = conn.prepareStatement(query);
			orderStmt.setString(1, userId);
			orderStmt.setString(2, item.getProductId());
			orderStmt.setString(3, item.getItemTotalValue());
			orderStmt.setString(4, product.getCatalog());
			orderStmt.setString(5, order_date);
			orderStmt.executeUpdate();
			orderStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isAddressValid() {
		String name = addressField.getText().trim();
		if (name.length() > 0) {
			return true;
		}
		return false;
	}
	public static boolean isValid(long number) {
		// TODO: write your code here
		
		int num_size = getSize(number);
		if(num_size >=13 && num_size <= 16)
		{
			if(prefixMatched(number,4) || prefixMatched(number, 5) || prefixMatched(number , 6) || prefixMatched(number , 37))
			{
				int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
				if(sum%10 == 0)
					return true;
				else
					System.out.println("Number cannot be a logical credit card number");
			}
			else
				System.out.println("Number does not match with any recognized credit card brand");
		}
		else
			System.out.println("Number of digits does not correspond to a valid card number");
	
		return false;
	}
	/** Get the result from Step 2 */
	public static int sumOfDoubleEvenPlace(long number) {
		// TODO: write your code here
		int sum = 0;
		String num = String.valueOf(number);
		for(int i = 0; i<num.length() ; i = i+2)
			sum = sum + getDigit(Integer.parseInt(""  +num.charAt(i)));
		
		return sum;
	}
	/**
	 * Return this number if it is a single digit, otherwise, return the sum of
	 * the two digits
	 */
	public static int getDigit(int number) {
		// TODO: write your code here
		
		number = number * 2;
		if(number<10)
			return number;
		
		return number%10 + number/10 ;
	}

	/** Return sum of odd place digits in number */
	public static int sumOfOddPlace(long number) {
		// TODO: write your code here
		int sum = 0;
		String num = String.valueOf(number);
		for(int i = 1; i <num.length(); i = i + 2)
			sum = sum + Integer.parseInt(""+num.charAt(i));
		return sum;
	}
	/** Return true if the digit d is a prefix for number */
	public static boolean prefixMatched(long number, int d) {
		if(d == getPrefix(number,1 ) || d == getPrefix(number,2))
			return true;
		
		return false;
	}
	/** Return the number of digits in d */
	public static int getSize(long d) {
		// TODO: write your code here
		String num = String.valueOf(d);
		return num.length();
	}
	/**
	 * Return the first k number of digits from number. If the number of digits
	 * in number is less than k, return number.
	 */
	public static long getPrefix(long number, int k) {
		// TODO: write your code here
		String num = String.valueOf(number);
		if(num.length()>k)
			return(Long.parseLong(num.substring(0,k)));
		
		return number;
	}
}
