package com.Decor.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.Decor.entities.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer>{
	
	@Query("from Orders where u_id=?1")
	public List<Orders> findByCustomer(int id);
}