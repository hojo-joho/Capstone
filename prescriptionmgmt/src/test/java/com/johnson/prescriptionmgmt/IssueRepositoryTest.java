package com.johnson.prescriptionmgmt;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.repository.IssueRepository;

@DataJpaTest
@Rollback(false)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class IssueRepositoryTest {
	
	@Autowired
	IssueRepository issueRepo;
	
	@Test
	public void testFindAllUnresolvedIssues() {
		
		Issue issue = new Issue();
		issue.setIssueTxt("test1");
		issue.setResolved(true);
		
		Issue issue2 = new Issue();
		issue2.setIssueTxt("test2");
		issue2.setResolved(false);
		
		issueRepo.save(issue);
		issueRepo.save(issue2);
		
		List<Issue> unresolved = issueRepo.findAllUnresolvedIssues();
		
		Assertions.assertThat(unresolved).doesNotContain(issue);
		Assertions.assertThat(unresolved).contains(issue2);
		System.out.println("test ran");
	}

}
