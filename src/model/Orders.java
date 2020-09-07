package model;

public class Orders {
	private String od_ID;
	private String od_CT_ID;
	private String od_EM_ID;
	private String od_OrderDate;
	private String od_Status;
	
	public Orders() {
		super();
	}

	public Orders(String od_ID, String od_CT_ID, String od_EM_ID, String od_OrderDate, String od_Status) {
		super();
		this.od_ID = od_ID;
		this.od_CT_ID = od_CT_ID;
		this.od_EM_ID = od_EM_ID;
		this.od_OrderDate = od_OrderDate;
		this.od_Status = od_Status;
	}

	public String getOd_ID() {
		return od_ID;
	}

	public void setOd_ID(String od_ID) {
		this.od_ID = od_ID;
	}

	public String getOd_CT_ID() {
		return od_CT_ID;
	}

	public void setOd_CT_ID(String od_CT_ID) {
		this.od_CT_ID = od_CT_ID;
	}

	public String getOd_EM_ID() {
		return od_EM_ID;
	}

	public void setOd_EM_ID(String od_EM_ID) {
		this.od_EM_ID = od_EM_ID;
	}

	public String getOd_OrderDate() {
		return od_OrderDate;
	}

	public void setOd_OrderDate(String od_OrderDate) {
		this.od_OrderDate = od_OrderDate;
	}

	public String getOd_Status() {
		return od_Status;
	}

	public void setOd_Status(String od_Status) {
		this.od_Status = od_Status;
	}
}
