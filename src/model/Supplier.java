package model;

public class Supplier {
	private String su_ID;
	private String su_Name;
	private String su_PhoneNumber;
	private String su_Address;
	private String su_Status;
	
	public String getSu_ID() {
		return su_ID;
	}
	
	public void setSu_ID(String su_ID) {
		this.su_ID = su_ID;
	}
	
	public String getSu_Name() {
		return su_Name;
	}
	
	public void setSu_Name(String su_Name) {
		this.su_Name = su_Name;
	}
	
	public String getSu_PhoneNumber() {
		return su_PhoneNumber;
	}
	
	public void setSu_PhoneNumber(String su_PhoneNumber) {
		this.su_PhoneNumber = su_PhoneNumber;
	}
	
	public String getSu_Address() {
		return su_Address;
	}
	
	public void setSu_Address(String su_Address) {
		this.su_Address = su_Address;
	}
	
	public String getSu_Status() {
		return su_Status;
	}
	
	public void setSu_Status(String su_Status) {
		this.su_Status = su_Status;
	}
	
	public Supplier(String su_ID, String su_Name, String su_PhoneNumber, String su_Address, String su_Status) {
		super();
		this.su_ID = su_ID;
		this.su_Name = su_Name;
		this.su_PhoneNumber = su_PhoneNumber;
		this.su_Address = su_Address;
		this.su_Status = su_Status;
	}

	public Supplier() {
		super();
	}
}
