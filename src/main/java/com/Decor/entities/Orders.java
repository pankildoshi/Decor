package com.Decor.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private int oId;
	@Column(length = 10)
	private double oPrice;
	@ManyToOne
	@JoinColumn(name = "uId")
	private Customer customer;
	@Column(length=10)
	private String oDate;
	@Column(length=100)
	private String shippingAddress;
	@OneToMany(mappedBy = "order")
	private List<OrderedItems> orderItems = new ArrayList<>();
	
	public Orders() {}

	public Orders(int oId, double oPrice, Customer customer, String oDate, String shippingAddress,
			List<OrderedItems> orderItems) {
		super();
		this.oId = oId;
		this.oPrice = oPrice;
		this.customer = customer;
		this.oDate = oDate;
		this.shippingAddress = shippingAddress;
		this.orderItems = orderItems;
	}

	public double getoPrice() {
		return oPrice;
	}

	public void setoPrice(double oPrice) {
		this.oPrice = oPrice;
	}

	public int getoId() {
		return oId;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getoDate() {
		return oDate;
	}

	public void setoDate(String oDate) {
		this.oDate = oDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderedItems> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderedItems> orderItems) {
		this.orderItems = orderItems;
	}	
	
}