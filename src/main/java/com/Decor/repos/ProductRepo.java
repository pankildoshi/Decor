package com.Decor.repos;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Decor.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer> {

	@Modifying
	@Transactional
	@Query("update Product p set p.qty= :qty where p.id= :id")
	public void setQuantityById(@Param(value = "qty") int qty, @Param(value = "id") int id);
}