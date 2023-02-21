package com.johnson.prescriptionmgmt;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import com.johnson.prescriptionmgmt.model.Pharmacy;
import com.johnson.prescriptionmgmt.repository.PharmacyRepository;
import com.johnson.prescriptionmgmt.service.PharmacyService;


@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PharmacyServiceTest {
	
	@Autowired
	PharmacyRepository pharmRepo;
	
	PharmacyService pharmService = new PharmacyService(pharmRepo);
	

	@Test
	public void testNewPharmacy() {
		
		Pharmacy pharmacy = new Pharmacy(2000, "Test pharm", "555-555-5555", "M-F 9-5");
		
		pharmRepo.save(pharmacy);
		
		Optional<Pharmacy> saved = pharmRepo.findById(2000);
		
		Assertions.assertThat(saved.isPresent());
	}
	
	@Test
	public void testUpdatePharmacy() {
		
		Pharmacy pharmacy = new Pharmacy("Test pharm two", "555-555-5555", "success");
		
		int id = 2000;
		
		pharmService.updatePharmacy(id, pharmacy);
		
		Optional<Pharmacy> updated = pharmRepo.findById(id);
		
		Assertions.assertThat(updated.get().getPharmacyName()).isEqualTo("Test pharm two");
	}
	
}
