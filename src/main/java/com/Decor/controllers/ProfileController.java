package com.Decor.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.Decor.entities.Customer;
import com.Decor.repos.CustomerRepo;
import com.Decor.services.CustomerService;

@Controller
public class ProfileController {
	
	
	@Autowired
	private CustomerService customerserv;
	@Autowired
	private CustomerRepo repo;
	
	@GetMapping("/customer_profile")
	public ModelAndView showProfile(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			int id = (int)session.getAttribute("customerId");
			mv.addObject("id",id);
			Customer customer=customerserv.getCustomer(id);
			mv.addObject("customer", customer);
			mv.setViewName("customer/profile");
		}
		else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
	
	@GetMapping("/customer_profile_update")
	public ModelAndView updateProfile(HttpSession session)
	{
		ModelAndView mv = new ModelAndView();
		if(session.getAttribute("customerId") != null) {
			int id = (int)session.getAttribute("customerId");
			mv.addObject("id",id);
			Customer customer=customerserv.getCustomer(id);
			mv.addObject("customer", customer);
			mv.setViewName("customer/updateprofile");
		}else {
			mv.setViewName("redirect:/customer_login");
		}
		return mv;
	}
         @PostMapping("/customer_profile_update")
         public ModelAndView UpdateCustomer(HttpSession session, @ModelAttribute("customer") Customer customer)
         {
        	ModelAndView mv = new ModelAndView();
     		if(session.getAttribute("customerId") != null) {
	        	 int id = (int)session.getAttribute("customerId");
	     		 repo.setCustomerInfoById(customer.getName(),customer.getEmail(),customer.getDob(),customer.getPassword(),customer.getPhone()
	     				   ,customer.getAddress(),id);
	     		 mv.setViewName("customer/profile");
     		}else {
    			 mv.setViewName("redirect:/customer_login");
    		}
    		return mv;	
         } 
	}
