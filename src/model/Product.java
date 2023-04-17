package model;

public class Product {
	
	private int qty;
	private double price;
	private String productName;
	private String productId;
	private String catalog;

	// Product entity is used to store the product information from the Inventory Database store.
	public Product(String productId, String productName, int qty, double price, String catalogId){
		this.productId = productId;
		this.productName = productName;
		this.qty = qty;
		this.price = price;
		this.catalog = catalogId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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
	
	@Override
	public String toString() {
		return "Product{" + "productId=" + productId + "productName=" + productName + "quantity=" + qty + "price=" + price +'}';
		
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}
	
}
