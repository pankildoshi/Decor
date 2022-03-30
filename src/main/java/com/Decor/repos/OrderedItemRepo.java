package com.Decor.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Decor.entities.OrderedItems;

public interface OrderedItemRepo extends JpaRepository<OrderedItems, Integer>{
	@Query("from OrderedItems where o_id=?1")
	public List<OrderedItems> findByOrder(int id);
}