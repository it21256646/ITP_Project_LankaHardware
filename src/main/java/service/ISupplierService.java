package service;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gson.JsonElement;

import model.Supplier;

public interface ISupplierService {
	
	public static final Logger log = Logger.getLogger(ISupplierService.class.getName());
	
	public ArrayList<Supplier> getAllSuppliers();
	
	public String addSuppliers(Supplier supplier);

	public String removeSuppliers(String supNo);

	public String updateSuppliers(String supNo, String name, String email, String description, String debit);

	public String sendSupplierDetails(String supNo);

	

}
