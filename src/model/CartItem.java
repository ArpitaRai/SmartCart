package model;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CartItem {

	private String productId;
	private String productName;
	private int qty;
	private double price;

	private String totalValueItem;
	
	DecimalFormat df = new DecimalFormat("0.00");
	
    // CartItem entity holds the product information that is added to the cart by user
	public CartItem(String productId, String name, int qty, double price) {
        this.productId = productId;
        this.productName = name;
        this.qty = qty;
        this.price = price;
        this.setTotalValueItem();
    }
	
	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
		setTotalValueItem();
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@Override
    public String toString() {
        return "Product{" + "productId=" + productId + "productName=" + productName + "quantity=" + qty + "price=" + price +'}';
    }


	public String getTotalValueItem() {
		return totalValueItem;
	}


	public void setTotalValueItem() {
		this.totalValueItem = df.format((double) this.qty * this.price);
	}
	
	
}
