package com.johnson.prescriptionmgmt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class IndexPageController {
	
	static final String LOGIN = "/login";
	static final String ERROR = "error";
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/login")
	public String goToLogin() {
		return "login";
	}
	
	@PostMapping("/login")
	public String validateLogin(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		
		//if password and email match redirect to home
		Optional<User> dbuser = userService.findUserByEmail(email);
		
		if(dbuser.isPresent()) {
			
			if (dbuser.get().getPassword().equals(password)) {
				return "redirect:/home";
			} else {
				model.addAttribute(ERROR, "Password cannot be authenticated");
				return LOGIN;
			}
			
		} else {
			model.addAttribute(ERROR, "There is no account associated with that email");
			return LOGIN;
		}
		
	}
	
	
	@RequestMapping("/register")
	public String goToRegister(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@RequestMapping("/index")
	public String goToIndex() {
		return "index";
	}
	
	@PostMapping("/register/newuser")
	public String registerUser(@ModelAttribute("user") User user, Model model, BindingResult res) {
		
		Optional<User> db_user= userService.findUserByEmail(user.getEmail());
		
		if (db_user.isPresent()) {
			res.rejectValue("email", null, "This email is associated with an existing account");
		} if (res.hasErrors()) {
			model.addAttribute(ERROR, "error");
			model.addAttribute("user", user);
			return "/register";
		} else {
			userService.saveUser(user);
			return LOGIN;
		}
	}
}