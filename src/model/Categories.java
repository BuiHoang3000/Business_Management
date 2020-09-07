package model;

public class Categories {
	private String ca_ID;
	private String ca_Name;
	private String ca_Status;
	
	public Categories(String ca_ID, String ca_Name, String ca_Status) {
		super();
		this.ca_ID = ca_ID;
		this.ca_Name = ca_Name;
		this.ca_Status = ca_Status;
	}

	public Categories() {
		super();
	}

	public String getCa_ID() {
		return ca_ID;
	}

	public void setCa_ID(String ca_ID) {
		this.ca_ID = ca_ID;
	}

	public String getCa_Name() {
		return ca_Name;
	}

	public void setCa_Name(String ca_Name) {
		this.ca_Name = ca_Name;
	}

	public String getCa_Status() {
		return ca_Status;
	}

	public void setCa_Status(String ca_Status) {
		this.ca_Status = ca_Status;
	}
}
