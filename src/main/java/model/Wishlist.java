package model;

import java.util.ArrayList;

public class Wishlist {

	private String wishlistID;
	private ArrayList<Item> items;

	public String getWishlistID() {
		return wishlistID;
	}

	public void setWishlistID(String wishlistID) {
		this.wishlistID = wishlistID;
	}

	public ArrayList<Item> getItems() {
		return items;
	}

	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
}
