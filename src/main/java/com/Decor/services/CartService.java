package com.Decor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.Cart;
import com.Decor.repos.CartRepo;

@Service
public class CartService {
	@Autowired
	private CartRepo repo;
	
	public void addCart(Cart cart) {
		repo.save(cart);
	}
	
	public void deleteCart(int cartId) {
		repo.deleteById(cartId);
	}
	
	public void deleteCartByProduct(int pid, int cid) {
		repo.deleteCartByProductAndCustomer(pid, cid);
	}
	
	public void updateCart(Cart cart) {
		repo.setQuantityById(cart.getQuantity(), cart.getCartId());
		repo.setPriceById(cart.getCartPrice(), cart.getCartId());
	}
	
	public List<Cart> getCartByCustomer(int id) {
		return repo.findByCustomer(id);
	}
}
