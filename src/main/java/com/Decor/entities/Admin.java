package com.Decor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "admin")
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name="Address")
	private String Address;
	@Column(name="dob")
	private String dob;

	public Admin() {
	}

	public Admin(Integer id, String username, String password,String Address,String dob) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.Address = Address;
		this.dob = dob;
	}

	public Integer getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getAddress() {
		return Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}
	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}