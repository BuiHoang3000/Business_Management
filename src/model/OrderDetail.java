package model;

public class OrderDetail {
	private String de_OD_ID;
	private String de_PR_ID;
	private String de_Quantify;
	private String de_Price;
	private String de_Status;
	
	public OrderDetail() {
		super();
	}

	public OrderDetail(String de_OD_ID, String de_PR_ID, String de_Quantify, String de_Price, String de_Status) {
		super();
		this.de_OD_ID = de_OD_ID;
		this.de_PR_ID = de_PR_ID;
		this.de_Quantify = de_Quantify;
		this.de_Price = de_Price;
		this.de_Status = de_Status;
	}

	public String getDe_OD_ID() {
		return de_OD_ID;
	}

	public void setDe_OD_ID(String de_OD_ID) {
		this.de_OD_ID = de_OD_ID;
	}

	public String getDe_PR_ID() {
		return de_PR_ID;
	}

	public void setDe_PR_ID(String de_PR_ID) {
		this.de_PR_ID = de_PR_ID;
	}

	public String getDe_Quantify() {
		return de_Quantify;
	}

	public void setDe_Quantify(String de_Quantify) {
		this.de_Quantify = de_Quantify;
	}

	public String getDe_Price() {
		return de_Price;
	}

	public void setDe_Price(String de_Price) {
		this.de_Price = de_Price;
	}

	public String getDe_Status() {
		return de_Status;
	}

	public void setDe_Status(String de_Status) {
		this.de_Status = de_Status;
	}
}