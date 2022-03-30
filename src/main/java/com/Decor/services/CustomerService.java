package com.Decor.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.Customer;
import com.Decor.repos.CustomerRepo;

@Service
public class CustomerService {
	@Autowired
	private CustomerRepo repo;
	
	public void addCustomer(Customer customer) {
		repo.save(customer);
	}
	
	public List<Customer> getCustomers(){
		return repo.findAll();
	}
	
	public Customer getCustomer(int id) {
		return repo.getById(id);
	}
	
	public void deleteCustomer(int id) {
		repo.deleteById(id);
	}
	
	public int totalCustomers() {
		return (int) repo.count();
	}
}
