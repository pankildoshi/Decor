package com.Decor.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Decor.entities.Admin;
import com.Decor.repos.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepo;
	
	public Admin login(String username, String password) {
		Admin admin = adminRepo.findByUsernamePassword(username, password);
		return admin;
	}
	
	public void addAdmin(Admin admin) {
		adminRepo.save(admin);
	}
}
