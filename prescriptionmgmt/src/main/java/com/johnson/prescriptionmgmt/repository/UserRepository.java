package com.johnson.prescriptionmgmt.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.johnson.prescriptionmgmt.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	  Optional<User> findByEmail(String email);
	  
	  @Query("select u from User u ORDER BY u.fullName")
	  List<User> findAllUserAlphabetical();

}
