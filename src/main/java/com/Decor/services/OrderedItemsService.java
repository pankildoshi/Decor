package com.Decor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.OrderedItems;
import com.Decor.entities.Orders;
import com.Decor.repos.OrderedItemRepo;

@Service
public class OrderedItemsService {
	@Autowired
	OrderedItemRepo repo;
	
	public void addOrderedItem(OrderedItems item) {
		repo.save(item);
	}
	
	public void deleteOrderedItem(OrderedItems item) {
		repo.delete(item);
	}
	
	public List<OrderedItems> getOrderedItems(Orders order){
		return repo.findByOrder(order.getoId());
	}
}