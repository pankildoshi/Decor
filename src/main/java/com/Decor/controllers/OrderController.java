package com.Decor.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Decor.entities.Cart;
import com.Decor.entities.Customer;
import com.Decor.entities.OrderedItems;
import com.Decor.entities.Orders;
import com.Decor.entities.Product;
import com.Decor.services.CartService;
import com.Decor.services.CustomerService;
import com.Decor.services.OrderService;
import com.Decor.services.OrderedItemsService;
import com.Decor.services.ProductService;

@Controller
public class OrderController {
	@Autowired
	ProductService productService;
	@Autowired
	CustomerService customerService;
	@Autowired
	CartService cartService;
	@Autowired
	OrderService orderService;
	@Autowired
	OrderedItemsService orderedItemsService;
	
	@RequestMapping("/displayOrder")
	public ModelAndView displayOrder(HttpSession session) {
		ModelAndView mv = new ModelAndView();
		int customerId = (int) session.getAttribute("customerId");
		
		if(session.getAttribute("customerId") != null) {
			List<Orders> orders = orderService.getOrdersByCustomer(customerId);
			mv.addObject("orders", orders);
			mv.setViewName("customer/orderHistory");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
	
	@RequestMapping("/placeOrder")
	public ModelAndView placeOrder(ModelMap modelMap, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("customerId") != null) {
			List<Cart> allcarts =  cartService.getCartByCustomer((int) session.getAttribute("customerId"));
			Customer customer = customerService.getCustomer((int) session.getAttribute("customerId"));
			
			Orders order = new Orders();
			order.setCustomer(customer);
			order.setShippingAddress(customer.getAddress());
			order.setoDate(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
			double orderPrice = 0;
			for(Cart cart: allcarts) {
				orderPrice += cart.getCartPrice();
			}
			order.setoPrice(orderPrice);
			orderService.addOrder(order);
			
			List<OrderedItems> orderedItems = new ArrayList<>();
			for(Cart cart: allcarts) {
				Product product = productService.getProduct(cart.getProduct().getId());
				
				OrderedItems oItem = new OrderedItems();
				oItem.setOrder(order);
				oItem.setProduct(product);
				oItem.setpQuantity(cart.getQuantity());
				oItem.setpPrice(cart.getCartPrice());
				orderedItems.add(oItem);
				orderedItemsService.addOrderedItem(oItem);
				
				product.setQty(product.getQty()-cart.getQuantity());
				productService.updateQty(product);
				
				cartService.deleteCart(cart.getCartId());
			}
			
			modelMap.addAttribute("orderedItems", orderedItems);
			modelMap.addAttribute("order", order);
			mv.setViewName("customer/confirmOrder");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		
		return mv;
	}	
	
	@RequestMapping("/confirmOrder")
	public String confirmOrder(HttpSession session) {
		if(session.getAttribute("customerId") != null) {
			return "redirect:/customer";
		}
		return "redirect:/customer_login";
	}
	
	@RequestMapping("/cancelOrder")
	public ModelAndView cancelOrder(@RequestParam("orderId") int oid, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			Orders order = orderService.getOrder(oid);
			List<OrderedItems> items = orderedItemsService.getOrderedItems(order);
			int customerId = (int) session.getAttribute("customerId");
			for(OrderedItems item : items) {
				Cart cart = new Cart();
				cart.setCartPrice(item.getpPrice());
				cart.setQuantity(item.getpQuantity());
				cart.setProduct(item.getProduct());
				cart.setCustomer(customerService.getCustomer(customerId));
				cartService.addCart(cart);
				orderedItemsService.deleteOrderedItem(item);
			}
			orderService.deleteOrder(order);
			mv.addObject("Success", "Order Cancelled.");
			mv.setViewName("redirect:/displayCart");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
	
	@RequestMapping("/detailedOrder/{id}")
	public ModelAndView detailedOrder(@PathVariable("id") int id, ModelMap modelMap, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		if(session.getAttribute("customerId") != null) {
			Orders order = orderService.getOrder(id);
			List<OrderedItems> items = orderedItemsService.getOrderedItems(order);
			modelMap.addAttribute("order", order);
			modelMap.addAttribute("orderedItems", items);
			mv.setViewName("customer/orderDetails");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		
		return mv;
	}
}