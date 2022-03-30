package com.Decor.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String email;
	private String dob;
	private String phone;
	private String address;
	private String Password;

	public Customer() {
		super();
	}

	public Customer(String name, String email, String dob, String phone, String address,String Password) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.Password = Password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return Password;
	}

	public void setPassword(String Password) {
		this.Password = Password;
	}
	

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", phone=" + phone
				+ ", address=" + address + "]";
	}
}
