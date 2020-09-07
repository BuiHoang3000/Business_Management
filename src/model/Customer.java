package model;

public class Customer extends Person {
	private String CT_Rank;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(String ps_ID, String ps_Name, String ps_Email, String ps_Password, String ps_BirthDate,
			String ps_Adress, String ps_PhoneNumber, String ps_Status, String cT_Rank) {
		super(ps_ID, ps_Name, ps_Email, ps_Password, ps_BirthDate, ps_Adress, ps_PhoneNumber, ps_Status);
		this.CT_Rank = cT_Rank;
	}

	public String getCT_Rank() {
		return CT_Rank;
	}

	public void setCT_Rank(String cT_Rank) {
		this.CT_Rank = cT_Rank;
	}
}
