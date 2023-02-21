package com.johnson.prescriptionmgmt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnson.prescriptionmgmt.model.Issue;
import com.johnson.prescriptionmgmt.repository.IssueRepository;

@Service
public class IssueService {
	
	@Autowired
	IssueRepository issueRepo;
	
	public void saveIssue(Issue issue) {
		issueRepo.save(issue);
	}
	
	public List<Issue> findAllIssues(){
		return issueRepo.findAll();
	}
	
	public Issue findById(int id) {
		return issueRepo.getReferenceById(id);
	}
	
	public void deleteIssue(int id) {
		issueRepo.deleteById(id);
	}
	
	public List<Issue> findAllUnresolvedIssues(){
		return issueRepo.findAllUnresolvedIssues();
	}
	
}
