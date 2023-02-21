package com.johnson.prescriptionmgmt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.service.MedicationService;
import com.johnson.prescriptionmgmt.service.UserService;

@Controller
public class PharmacistController {
	
	@Autowired 
	MedicationService medService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("/refills")
	public String viewRefills(Model model) {

		List<Medication> rxlist = medService.findAllMedsNeedingRefills();
		if (rxlist.isEmpty()) {
			model.addAttribute("norefills", "There are no Prescriptions to refill at this time.");
			return "/refills";
		} else {
			model.addAttribute("rxList", rxlist);
			return "/refills";
		} 
	
	}
	
	@RequestMapping("/users")
	public String viewUsers(Model model) {

		List<User> users = userService.findAllUsers();
		if (users.isEmpty()) {
			model.addAttribute("nousers", "There are no users in the program.");
			return "/users";
		} else {
			model.addAttribute("users", users);
			return "/users";
		} 
	
	}

}
