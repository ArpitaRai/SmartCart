package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Cart {

	private HashMap<String, CartItem> items = new HashMap<String, CartItem>();
	
	// Cart Object is used to hold the products list added to the cart by the user in a cartItems Hashmap
	public Cart() {
	}

	// Returns the products List in a ArrayList to present the data in the Order Summary table
	public List<CartItem> getItems() {
		List<CartItem> listOfItems = new ArrayList<CartItem>();
		Iterator<Entry<String, CartItem>> cartItemsIterator = items.entrySet().iterator();
		// Iterating every set of entry in the HashMap
		while (cartItemsIterator.hasNext()) {
			Map.Entry<String, CartItem> entity = cartItemsIterator.next();
			listOfItems.add(entity.getValue());
		}
		return listOfItems;
	}

	public Set<String> getProductKeys() {
		return items.keySet();
	}

	public void addProduct(CartItem entity) {

		if (items.containsKey(entity.getProductId())) {
			int qty = entity.getQty();

			items.get(entity.getProductId()).setQty(qty);

			System.out.println(items.get(entity.getProductId()).getQty());
			//cartItems.put(entity.getProductId(), cartItems.get(entity));
			items.put(entity.getProductId(), entity);

		} else {
			items.put(entity.getProductId(), entity);
		}

	}

	public void clearCart() {
		items.clear();
	}

}
