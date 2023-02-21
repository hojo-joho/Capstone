package com.johnson.prescriptionmgmt.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.service.IssueService;
import com.johnson.prescriptionmgmt.service.MedicationService;
import com.johnson.prescriptionmgmt.service.PharmacyService;
import com.johnson.prescriptionmgmt.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	IssueService issueService;
	
	@Autowired
	MedicationService medService;
	
	@Autowired
	PharmacyService pharmService;
	
	@Autowired 
	UserService userService;	

	
	@RequestMapping("/logout")
	public String logout(Model model) {
		model.addAttribute("logoutMsg", "You have been logged out.");
		return "logout";
	}
	
	
	@RequestMapping("/home")
	public String goHome(Model model) {
	
		Issue issue = new Issue();
		model.addAttribute(issue);
		
//		Optional<User> user = userService.findUserById(id);
//		
//		if(user.isPresent()) {
//			User currentUser = user.get();
//			List<Medication> userMeds = medService.findMedsByUser(currentUser);
//			if (userMeds.isEmpty()) {
//				return "/home";
//			} else {
//				model.addAttribute("rxList", userMeds);
//				return "/home";
//			}
		List<Medication> rxlist = medService.findAll();
		if (rxlist.isEmpty()) {
			return "/home";
		} else {
			model.addAttribute("rxList", rxlist);
			return "/home";
		} 
	
	}
	
	
	@GetMapping("/home/delete/{id}")
	public String deleteMedication(@PathVariable("id") Integer id) {
		medService.deleteMed(id);
		return "redirect:/home";
	}
	
	@PostMapping("/issue/save")
	public RedirectView addIssue(@ModelAttribute("issue") Issue issue, Model model, RedirectAttributes redirectAttributes, RedirectView redirectView, BindingResult res) {
		if (res.hasErrors()) {
			model.addAttribute("error", "error");
			return new RedirectView("/home");
		}else {
			redirectAttributes.addFlashAttribute("message", "Thanks for letting us know!");
		    redirectAttributes.addFlashAttribute("alertClass", "alert-success");
		    issue.setResolved(false);
			issueService.saveIssue(issue);
			return new RedirectView("/home");
		}
	}

}
