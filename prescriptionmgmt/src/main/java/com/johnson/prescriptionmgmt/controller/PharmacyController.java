package com.johnson.prescriptionmgmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.service.PharmacyService;

@Controller
public class PharmacyController {
	
	@Autowired
	PharmacyService pharmService;

	//currently hardcoded until Security and session mgmt are in place	
		@RequestMapping("/pharmacy")
		public String goToPharmacy(Model model) {
			Pharmacy pharmacy = pharmService.findPharmacyById(1);
			
			model.addAttribute("pharmacyName", pharmacy.getPharmacyName());
			model.addAttribute("pharmacyPhone", pharmacy.getPharmacyPhone());
			model.addAttribute("pharmacyHours", pharmacy.getPharmacyHours());
			return "pharmacy";
		}
}
