package com.Decor.repos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Decor.entities.Cart;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer>{

	@Query("from Cart where c_id=?1")
	public List<Cart> findByCustomer(int id);
	
	@Modifying
	@Transactional
	@Query("update Cart c set c.quantity= :quantity where c.cartId= :cartId")
	public void setQuantityById(@Param(value = "quantity") int quantity, @Param(value = "cartId") int cartId);
	
	@Modifying
	@Transactional
	@Query("update Cart c set c.cartPrice= :price where c.cartId= :cartId")
	public void setPriceById(@Param(value = "price") double price, @Param(value = "cartId") int cartId);
	
	public void deleteCartByProductAndCustomer(int pid, int cid);
}
