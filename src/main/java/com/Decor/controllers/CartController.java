package com.Decor.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Decor.entities.Cart;
import com.Decor.entities.Customer;
import com.Decor.entities.Product;
import com.Decor.services.CartService;
import com.Decor.services.CustomerService;
import com.Decor.services.ProductService;

@Controller
public class CartController {

	@Autowired
	ProductService productService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CartService cartService;
	
	@RequestMapping("/addCart")
	public ModelAndView addCart(@RequestParam("qty") int qty, 
			@RequestParam("pid") int pid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			List<Cart> allCartList = cartService.getCartByCustomer((int)session.getAttribute("customerId"));

			Cart cart = new Cart();
			Customer customer = customerService.getCustomer((int)session.getAttribute("customerId"));
			
			Product product = productService.getProduct(pid);
			cart.setQuantity(qty);
			cart.setProduct(product);
			cart.setCustomer(customer);
			cart.setCartPrice(qty * product.getPrice());
			
			boolean isItemNew = true;
			if(allCartList.isEmpty()) {
				cartService.addCart(cart);
				mv.addObject("success_message","Cart is added successfully..");
				mv.setViewName("redirect:/customer");
			}
			else {
				for(Cart c:allCartList) {
					if( c.getProduct().getId() == product.getId() ) {			
						isItemNew = false;				
						int qua = c.getQuantity() + qty;
						if(qua>product.getQty()) {
							mv.addObject("error_message","Not sufficient quantity available");
							mv.setViewName("forward:/customer");
							return mv;
						}
						c.setQuantity(qua);
						c.setCartPrice(c.getCartPrice() + (qty * product.getPrice()));
						cartService.updateCart(c);
						mv.setViewName("redirect:/customer");
					}
				}
			}
			if(isItemNew) {
				cartService.addCart(cart);
				mv.addObject("success_message","Cart is added successfully..");
				mv.setViewName("redirect:/customer");
			}
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
	
	@GetMapping("/displayCart")
	public ModelAndView displayCart(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			List<Cart> allCartList = cartService.getCartByCustomer((int)session.getAttribute("customerId"));
			mv.addObject("items", allCartList);
			mv.setViewName("customer/cart");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
	
	@GetMapping("/deleteCart/{cartId}")
	public String deleteCart(@PathVariable("cartId") int cartId, HttpSession session) {
		if(session.getAttribute("customerId") != null) {
			cartService.deleteCart(cartId);
			return "redirect:/displayCart";
		}
		else {
			return "redirect:/customer_login";
		}
	}
}
