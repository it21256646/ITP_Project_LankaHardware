package model;

import java.util.ArrayList;

public class Customer {

	private String email;
	private String Password;
	private String phone;
	private String name;
	private String address;
	
	private ArrayList<String> profile;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getProfile() {
		return profile;
	}

	public void setProfile(ArrayList<String> profile) {
		this.profile = profile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	

}
