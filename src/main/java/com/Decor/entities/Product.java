package com.Decor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "product_name")
	private String pname;
	@Column(name = "description")
	private String pdesc;
	@Column(name = "price")
	private double price;
	@Column(name = "quantity")
	private int qty;
	@Column(name = "product_type")
	private String ptype;
	@Column(name = "image_url")
	private String imageURL;

	public Product() {
		super();
	}

	public Product(String pname, String pdesc, double price, int qty, String ptype, String imageURL) {
		super();
		this.pname = pname;
		this.pdesc = pdesc;
		this.price = price;
		this.qty = qty;
		this.ptype = ptype;
		this.imageURL = imageURL;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getPtype() {
		return ptype;
	}

	public void setPtype(String ptype) {
		this.ptype = ptype;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", pname=" + pname + ", pdesc=" + pdesc + ", price=" + price + ", qty=" + qty
				+ ", ptype=" + ptype + ", imageURL=" + imageURL + "]";
	}
}
