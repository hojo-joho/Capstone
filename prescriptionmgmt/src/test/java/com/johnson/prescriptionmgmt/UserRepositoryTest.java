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
import com.johnson.prescriptionmgmt.model.Role;
import com.johnson.prescriptionmgmt.repository.UserRepository;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepo;
	
	@Test
	public void testAddNew() {
		
		User testuser = new User();
		
		testuser.setFullName("Toaster");
		testuser.setEmail("Toasty@gmail.com");
		testuser.setPassword("toaster");
		
		User save = userRepo.save(testuser);
		
		Assertions.assertThat(save).isNotNull();
		Assertions.assertThat(save.getId()).isGreaterThan(0);
		
	}
	
//	@Test
//	public void testFindByEmail() {
//		
//		User testuser = new User();
//		
//		testuser.setFullName("Otter");
//		testuser.setEmail("Ootty@gmail.com");
//		testuser.setPassword("otter");
//		
//		User save = userRepo.save(testuser); 
//		
//		User retrieve = userRepo.findByEmail("Ootty@gmail.com");
//				
//		Assertions.assertThat(retrieve).isNotNull();
//		Assertions.assertThat(retrieve.getFullName()).isEqualTo("Otter");
//	}

}
