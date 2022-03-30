package com.Decor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private int cartId;
	@Column(length = 10)
	private int quantity;
	@Column(length = 100)
	private double cartPrice;
	@ManyToOne
	@JoinColumn(name = "pId")
	private Product product;
	@ManyToOne
	@JoinColumn(name = "cId")
	private Customer customer;
	
	public Cart() {
		super();
	}

	public Cart(int cartId, int quantity, int cartPrice, Product product, Customer customer) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.cartPrice = cartPrice;
		this.product = product;
		this.customer = customer;
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCartPrice() {
		return cartPrice;
	}

	public void setCartPrice(double cartPrice) {
		this.cartPrice = cartPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}