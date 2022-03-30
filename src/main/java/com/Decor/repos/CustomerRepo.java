package com.Decor.repos;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Decor.entities.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{
	
	@Query("from Customer where email=?1")
	public List<Customer> findByEmail(String email);
	
	@Query("from Customer where email=?1 and Password=?2")
	public Customer findByUsernamePassword(String email,String Password);
	
	//@Query("from Customer where id=?1")
	//public Customer getCustomer(int id);

	
	@Modifying
	@Transactional
	@Query("update Customer c set c.name=?1, c.email=?2, c.dob=?3, c.Password=?4, c.phone=?5, c.address=?6 where id=?7")
	public void setCustomerInfoById(String name, String email, String dob, String Password, String phone, String address, int id);
     
	
	
}


