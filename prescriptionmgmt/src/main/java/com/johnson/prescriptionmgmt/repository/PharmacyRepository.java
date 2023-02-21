package com.johnson.prescriptionmgmt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.Pharmacy;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Integer>{

//	public void updatePharmacy(int id, Pharmacy pharmacy);
}
