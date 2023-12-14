package model;

public class Voucher {
	private String id;
	private String code;
	private int amount;
	private String exp;
	
	
	public String getId() {
		return id;
	}
	public void setId(String itemID) {
		this.id = itemID;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	

}
