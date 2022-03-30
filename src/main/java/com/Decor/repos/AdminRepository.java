package com.Decor.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Decor.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
		
	@Query("from Admin where username=?1")
	public List<Admin> findByEmail(String email);
	
	@Query("from Admin where username=?1 and password=?2")
	public Admin findByUsernamePassword(String username,String password);
     

}