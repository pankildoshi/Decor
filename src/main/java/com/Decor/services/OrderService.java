package com.Decor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.Orders;
import com.Decor.repos.OrderRepo;

@Service
public class OrderService {
	@Autowired
	OrderRepo repo;
	
	public void addOrder(Orders order) {
		repo.save(order);
	}
	
	public Orders getOrder(int id) {
		return repo.getById(id);
	}
	
	public void deleteOrder(Orders order) {
		repo.delete(order);
	}
	
	public List<Orders> getOrdersByCustomer(int id){
		return repo.findByCustomer(id);
	}
}