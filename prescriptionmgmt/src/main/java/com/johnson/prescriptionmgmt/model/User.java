package com.johnson.prescriptionmgmt.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable=false)
	private String fullName;
	@Column(unique=true, nullable=false)
	private String email;
	@Column(nullable=false)
	private String password;
	
	@ManyToOne
	private Pharmacy pharmacy;
	
	@ManyToMany
	private List<Role> roles;
	
	public User (String fullName, String email, String password) {
		this.fullName=fullName;
		this.email=email;
		this.password=password;
	}
}
