package model;

public class Person {
	protected String ps_ID;
	protected String ps_Name;
	protected String ps_Email;
	protected String ps_Password;
	protected String ps_BirthDate;
	protected String ps_Address;
	protected String ps_PhoneNumber;
	protected String ps_Status;
	
	public Person(String ps_ID, String ps_Name, String ps_Email, String ps_Password, String ps_BirthDate,
			String ps_Address, String ps_PhoneNumber, String ps_Status) {
		super();
		this.ps_ID = ps_ID;
		this.ps_Name = ps_Name;
		this.ps_Email = ps_Email;
		this.ps_Password = ps_Password;
		this.ps_BirthDate = ps_BirthDate;
		this.ps_Address = ps_Address;
		this.ps_PhoneNumber = ps_PhoneNumber;
		this.ps_Status = ps_Status;
	}

	public Person(String ps_Email, String ps_Password) {
		super();
		this.ps_Email = ps_Email;
		this.ps_Password = ps_Password;
	}

	public Person() {
		super();
	}

	public String getPs_ID() {
		return ps_ID;
	}

	public void setPs_ID(String ps_ID) {
		this.ps_ID = ps_ID;
	}

	public String getPs_Name() {
		return ps_Name;
	}

	public void setPs_Name(String ps_Name) {
		this.ps_Name = ps_Name;
	}

	public String getPs_Email() {
		return ps_Email;
	}

	public void setPs_Email(String ps_Email) {
		this.ps_Email = ps_Email;
	}

	public String getPs_Password() {
		return ps_Password;
	}

	public void setPs_Password(String ps_Password) {
		this.ps_Password = ps_Password;
	}

	public String getPs_BirthDate() {
		return ps_BirthDate;
	}

	public void setPs_BirthDate(String ps_BirthDate) {
		this.ps_BirthDate = ps_BirthDate;
	}

	public String getPs_Address() {
		return ps_Address;
	}

	public void setPs_Address(String ps_Adress) {
		this.ps_Address = ps_Adress;
	}

	public String getPs_PhoneNumber() {
		return ps_PhoneNumber;
	}

	public void setPs_PhoneNumber(String ps_PhoneNumber) {
		this.ps_PhoneNumber = ps_PhoneNumber;
	}

	public String getPs_Status() {
		return ps_Status;
	}

	public void setPs_Status(String ps_Status) {
		this.ps_Status = ps_Status;
	}
}
