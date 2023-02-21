package com.johnson.prescriptionmgmt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pharmacy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pharmacyName;
	private String pharmacyPhone;
	private String pharmacyHours;
	
//	public Pharmacy (String pharmacyName, String pharmacyPhone, String pharmacyHours, List<User> users) {
//		this.pharmacyName=pharmacyName;
//		this.pharmacyHours=pharmacyHours;
//		this.pharmacyPhone=pharmacyPhone;
//		this.users=users;
//	}
	
	
	public Pharmacy (String pharmacyName, String pharmacyPhone, String pharmacyHours) {
		this.pharmacyName=pharmacyName;
		this.pharmacyHours=pharmacyHours;
		this.pharmacyPhone=pharmacyPhone;
	}
}
