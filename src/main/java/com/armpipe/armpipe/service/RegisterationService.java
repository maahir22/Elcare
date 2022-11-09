package com.armpipe.armpipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armpipe.armpipe.dao.CandidateDao;
import com.armpipe.armpipe.dto.Candidate;

@Service
public class RegisterationService {
	@Autowired
	private CandidateDao candidateDao;
	
	public void register(Candidate candidate) {
		candidateDao.save(candidate);
	}
	
	public void delete(Candidate candidate) {
		candidateDao.delete(candidate);
	}
	
	public void deleteByID(Long id) {
		candidateDao.deleteById(id);
	}
}
