package model;

public class Employee extends Person {
	private String em_Position;

	public Employee() {
		super();
	}
	
	public Employee(String ps_Email, String ps_Password) {
		super(ps_Email, ps_Password);
	}

	public Employee(String ps_ID, String ps_Name, String ps_Email, String ps_Password, String ps_BirthDate,
			String ps_Address, String ps_PhoneNumber, String em_Position, String ps_Status) {
		super(ps_ID, ps_Name, ps_Email, ps_Password, ps_BirthDate, ps_Address, ps_PhoneNumber, ps_Status);
		this.em_Position = em_Position;
	}

	public String getEm_Position() {
		return em_Position;
	}

	public void setEm_Position(String em_Position) {
		this.em_Position = em_Position;
	}
}
