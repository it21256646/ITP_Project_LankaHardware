package service;

import java.util.ArrayList;

import model.Voucher;

public interface IVoucherService {
	
public ArrayList<Voucher> getAllVouchers();
	
	public ArrayList<Voucher> GetAllgetAllVouchersSortBy(int sort);
	
	public ArrayList<Voucher> getSearchedgetAllVouchers(String searchDetails);
	
	public String addStockVoucher(Voucher voucher); 

	public String removeVoucher(String id);

	public String updateVoucher(String id, String code, int amount, String exp);


}
