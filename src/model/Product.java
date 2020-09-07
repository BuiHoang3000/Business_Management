package model;

public class Product {
	private String	pr_ID;
	private String	pr_CA_ID;
	private String	pr_Name;
	private float	pr_Price;
	private int		pr_Quantify;
	private String	pr_SU_ID;
	private String	pr_Status;
	
	public Product(String pr_ID, String pr_CA_ID, String pr_Name, float pr_Price, int pr_Quantify, String pr_SU_ID,
			String pr_Status) {
		super();
		this.pr_ID = pr_ID;
		this.pr_CA_ID = pr_CA_ID;
		this.pr_Name = pr_Name;
		this.pr_Price = pr_Price;
		this.pr_Quantify = pr_Quantify;
		this.pr_SU_ID = pr_SU_ID;
		this.pr_Status = pr_Status;
	}
	
	public Product(String pr_CA_ID, String pr_Name, float pr_Price, int pr_Quantify, String pr_SU_ID,
			String pr_Status) {
		super();
		this.pr_CA_ID = pr_CA_ID;
		this.pr_Name = pr_Name;
		this.pr_Price = pr_Price;
		this.pr_Quantify = pr_Quantify;
		this.pr_SU_ID = pr_SU_ID;
		this.pr_Status = pr_Status;
	}

	public Product() {
		super();
	}

	public String getPr_ID() {
		return pr_ID;
	}

	public void setPr_ID(String pr_ID) {
		this.pr_ID = pr_ID;
	}

	public String getPr_CA_ID() {
		return pr_CA_ID;
	}

	public void setPr_CA_ID(String pr_CA_ID) {
		this.pr_CA_ID = pr_CA_ID;
	}

	public String getPr_Name() {
		return pr_Name;
	}

	public void setPr_Name(String pr_Name) {
		this.pr_Name = pr_Name;
	}

	public float getPr_Price() {
		return pr_Price;
	}

	public void setPr_Price(float pr_Price) {
		this.pr_Price = pr_Price;
	}

	public int getPr_Quantify() {
		return pr_Quantify;
	}

	public void setPr_Quantify(int pr_Quantify) {
		this.pr_Quantify = pr_Quantify;
	}

	public String getPr_SU_ID() {
		return pr_SU_ID;
	}

	public void setPr_SU_ID(String pr_SU_ID) {
		this.pr_SU_ID = pr_SU_ID;
	}

	public String getPr_Status() {
		return pr_Status;
	}

	public void setPr_Status(String pr_Status) {
		this.pr_Status = pr_Status;
	}
}
