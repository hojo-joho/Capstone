package com.johnson.prescriptionmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepo;
	
//Find a user by their email or return an empty user
	public Optional<User> findUserByEmail(String email){
		Optional<User> user = userRepo.findByEmail(email);
		return (user);
	}

//Find a user by Id
	public Optional<User> findUserById(int id) {
		return userRepo.findById(id);
	}
	
//Create new User
	public void saveUser(User user) {
		userRepo.save(user);
	}
	
//Find all users
	public List<User> findAllUsers() {
		return userRepo.findAll();
	}

}
