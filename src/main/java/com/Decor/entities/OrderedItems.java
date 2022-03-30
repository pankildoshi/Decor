package com.Decor.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderedItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(length = 10)
	private int orderedItemId;
	@ManyToOne
	private Product product;
	@Column(length = 100)
	private int pQuantity;
	@Column(length = 100)
	private double pPrice;
	@ManyToOne
	@JoinColumn(name = "oId")
	private Orders order;
	
	public OrderedItems() {}

	public OrderedItems(int orderedItemId, Product product, int pQuantity, int pPrice, Orders order) {
		super();
		this.orderedItemId = orderedItemId;
		this.product = product;
		this.pQuantity = pQuantity;
		this.pPrice = pPrice;
		this.order = order;
	}

	public int getOrderedItemId() {
		return orderedItemId;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}

	public double getpPrice() {
		return pPrice;
	}

	public void setpPrice(double pPrice) {
		this.pPrice = pPrice;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}
}