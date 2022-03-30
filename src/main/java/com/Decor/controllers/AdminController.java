package com.Decor.controllers;

import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Decor.entities.*;
import com.Decor.repos.AdminRepository;
import com.Decor.services.*;

@Controller
public class AdminController {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private AdminRepository repo;
	
	
	@GetMapping("/")
	public String show_home_page()
	{
		return "admin/Home";
	}
	
	@GetMapping("/admin/Admin_login")
	public String show_admin_login()
	{
		return "admin/Admin_login";
	}
	
	@PostMapping("/admin_login")
	public ModelAndView login_user(@RequestParam("username") String username,@RequestParam("password") String password,
			HttpSession session,ModelMap modelMap,Model model)
	{
		
		Admin admin=repo.findByUsernamePassword(username, password);
		
		int totalProducts = productService.totalProducts();
		int totalCustomers = customerService.totalCustomers();
		ArrayList<Integer> stats = new ArrayList<Integer>(Arrays.asList(totalCustomers, totalProducts));
		ModelAndView mv = new ModelAndView();
		mv.addObject("stats", stats);
		
		if(admin!=null)
		{
			String aname=admin.getUsername();
			String apass=admin.getPassword();
		
			if(username.equalsIgnoreCase(aname) && password.equalsIgnoreCase(apass)) 
			{
				session.setAttribute("admin", aname);
				
				mv.setViewName("admin/index");
				return mv;
			}
			else 
			{
				modelMap.put("error", "Invalid Account");
				mv.setViewName("admin/Admin_login");
				return mv;
			}
		}
		else
		{
			modelMap.put("error", "Invalid Account");
			mv.setViewName("admin/Admin_login");
			return mv;
		}	 
	}	
	
	@RequestMapping("/admin")
	public String showIndex(Admin admin) {
		return "admin/Admin_login";
	}
		
	@RequestMapping("/logout")
	public String logout() {
		return "admin/Admin_login";
	}
	
//	Product Mapping
	@GetMapping("/admin/add_product")
	public String addProduct() {
		return "admin/add_product";
	}
	
	@PostMapping("/admin/add_product")
	public String addProduct(Product product) {
		productService.addProduct(product);
		return null;
	}
	
	@GetMapping("/admin/manage_product")
	public ModelAndView manageProduct() {
		ModelAndView mv = new ModelAndView("admin/manage_product");
		List<Product> products = productService.getProducts();
		mv.addObject("products", products);
		return mv;
	}
	
	@GetMapping("/admin/update_product/{id}")
	public ModelAndView updateProduct(@PathVariable("id") int id) {
		ModelAndView mv = new ModelAndView("admin/update_product");
		Product product = productService.getProduct(id);
		mv.addObject("product", product);
		productService.deleteProduct(id);
		return mv;
	}
	
	@PostMapping("/admin/update_product/update")
	public String updateProduct(Product product) {
		productService.addProduct(product);
		return "redirect:/admin/manage_product";
	}
		
	@GetMapping("/admin/delete_product/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		productService.deleteProduct(id);
		return "redirect:/admin/manage_product";
	}
	
//	Customer Mapping
	@GetMapping("/admin/manage_customer")
	public ModelAndView manageCustomer() {
		ModelAndView mv = new ModelAndView("admin/manage_customer");
		List<Customer> customers = customerService.getCustomers();
		mv.addObject("customers", customers);
		return mv;
	}
	
	@GetMapping("/admin/delete_customer/{id}")
	public String deleteCustomer(@PathVariable("id") int id) {
		customerService.deleteCustomer(id);
		return "redirect:/admin/manage_customer";
	}
	
}