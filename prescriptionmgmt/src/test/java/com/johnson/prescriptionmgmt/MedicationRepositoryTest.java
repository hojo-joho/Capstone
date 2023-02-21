package com.johnson.prescriptionmgmt;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.Medication;
import com.johnson.prescriptionmgmt.model.User;
import com.johnson.prescriptionmgmt.repository.MedicationRepository;
import com.johnson.prescriptionmgmt.repository.UserRepository;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MedicationRepositoryTest {

	@Autowired
	UserRepository userRepo;
	@Autowired
	MedicationRepository medRepo;
	
	@Test
	public void testAddNew() {
		
		Medication testmed = new Medication();
		
		testmed.setMedicationName("Drug#3"); 
		testmed.setDaysSupply("20");
		testmed.setDirections("Take with food");
		testmed.setNeedsRefill(true);
		
		Medication savemed = medRepo.save(testmed);
		
		Assertions.assertThat(savemed).isNotNull();
		Assertions.assertThat(savemed.getMedicationName()).isEqualTo(testmed.getMedicationName());
	}
	
	@Test
	public void testFindAllMedsNeedingRefills() {
		List<Medication> medList = new ArrayList<Medication>();
		
		Medication testmed = new Medication();
		
		testmed.setMedicationName("Drug#5"); 
		testmed.setDaysSupply("20");
		testmed.setDirections("Take at the beach");
		testmed.setNeedsRefill(true);
		
		medRepo.save(testmed);
		
		medList = medRepo.findAllMedsNeedingRefills();
		
		Assertions.assertThat(medList).isNotEmpty();
	}
	
	
	@Test
	public void testFindMedsByUser() {
		
		List<Medication> medList = new ArrayList<Medication>();
		
		User user = new User("Hillary", "H@G.com", "12345");
		userRepo.save(user);
		
		Medication testmed = new Medication("Drug#5","Take while skydiving", "20", true, "12/02/2022", user);
		Medication testmed2  = new Medication("Drug#10","Take while sleeping", "20", false, "2/14/2023", user);
		
		medRepo.save(testmed);
		medRepo.save(testmed2);
		
		List<Medication> returnMeds = new ArrayList();
		
		returnMeds = medRepo.findMedsByUser(user);
		
		Assertions.assertThat(returnMeds).isNotEmpty();
	}
}
