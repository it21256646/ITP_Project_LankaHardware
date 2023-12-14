package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class WishlistChart {

	private ArrayList<String> sizes;
	private LinkedHashMap<String, ArrayList<Integer>> counts;

	public ArrayList<String> getSizes() {
		return sizes;
	}

	public void setSizes(ArrayList<String> sizes) {
		this.sizes = sizes;
	}

	public LinkedHashMap<String, ArrayList<Integer>> getCounts() {
		return counts;
	}

	public void setCounts(LinkedHashMap<String, ArrayList<Integer>> counts) {
		this.counts = counts;
	}

}
