package com.Decor.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.Decor.entities.Customer;
import com.Decor.entities.Product;
import com.Decor.repos.CustomerRepo;
import com.Decor.services.CustomerService;
import com.Decor.services.ProductService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerserv;
	@Autowired
	private ProductService productService;
	@Autowired
	private CustomerRepo repo;
	
	
	@GetMapping("/customer_login")
	public String viewCustomerloginpage()
	{
		return "customer/login";
	}
		
	@GetMapping("/customer/Register")
	public String viewCustomerRegistrationpage(Model model)
	{
		model.addAttribute("customer",new Customer());
		return "customer/Register";
	}
	
	@PostMapping("/AddCustomer")
	public String addUser(@RequestParam("email") String email, Customer customer)
		{
			ModelAndView mv=new ModelAndView("success");
			List<Customer> list=repo.findByEmail(email);
			
			if(list.size()!=0)
			{
			mv.addObject("message", "Oops!  There is already a user registered with the email provided.");
			
			}
			else
			{
				customerserv.addCustomer(customer);
			mv.addObject("message","User has been successfully registered.");
			}
			
			return "customer/login";
		}
	
	@PostMapping("/customer_login")
	public ModelAndView login_user(@RequestParam("email") String email,@RequestParam("password") String Password,
			HttpSession session,ModelMap modelMap,Model model)
	{
		
		Customer customer=repo.findByUsernamePassword(email, Password);
		ModelAndView mv = new ModelAndView();
		
		if(customer!=null)
		{
			String aname=customer.getEmail();
			String apass=customer.getPassword();
		
			if(email.equalsIgnoreCase(aname) && Password.equalsIgnoreCase(apass)) 
			{
				int id = customer.getId();
				session.setAttribute("customerId", id);
//				System.out.println(session.getAttribute("customerId"));
				List<Product> products = productService.getProducts();
				mv.addObject("products", products);
				mv.setViewName("customer/index");
				return mv;  
			}
			else 
			{
				modelMap.put("error", "Invalid Account");
				mv.addObject("products", null);
				mv.setViewName("customer/login");
				return mv;
			}
		}
		else
		{
			modelMap.put("error", "Invalid Account");
			mv.addObject("products", null);
			mv.setViewName("customer/login");
			return mv;
		}
	}
	
	@RequestMapping("/customer")
	public ModelAndView customerIndex(HttpSession session){
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			List<Product> products = productService.getProducts();
			mv.addObject("products", products);
			mv.setViewName("customer/index");
		}
		else {
			mv.setViewName("customer/login");
		}
		return mv;
	}
}
