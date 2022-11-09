package com.armpipe.armpipe.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.server.PathParam;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armpipe.armpipe.dao.CandidateDao;
import com.armpipe.armpipe.dao.CustomerDao;
import com.armpipe.armpipe.dto.Candidate;
import com.armpipe.armpipe.dto.CustomPair;
import com.armpipe.armpipe.dto.Customer;
import com.armpipe.armpipe.dto.Gig;
import com.armpipe.armpipe.dto.GigRequest;
import com.armpipe.armpipe.enums.GIG_STATUS;

@RestController
public class CustomerGigController {
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private CandidateDao candidateDao;
	
	@PostMapping("/customer/addgig")
	public String addGig(@RequestBody GigRequest gigRequest) {
		
		Long id = gigRequest.getCustomerId();
		System.out.println(id);
		Customer customer = customerDao.findById(id).get();
		
		Gig gig = new Gig();
		gig.setProjectImage(gigRequest.getProjectImage());
		gig.setProjectName(gigRequest.getProjectName());
		gig.setProjectIndustry(gigRequest.getProjectIndustry());
		gig.setProjectType(gigRequest.getProjectType());
		gig.setDuration(gigRequest.getDuration());
		gig.setQuotationPrice(gigRequest.getQuotationPrice());
		gig.setSkillsRequired(gigRequest.getSkillsRequired());
		gig.setDescription(gigRequest.getDescription());
		gig.setGig_status(GIG_STATUS.ACTIVE);
		
		customer.setCurrent_gig(gig);
		customerDao.save(customer);
		return "Customer Gig Added";
	}
	
	@PostMapping("/customer/updategig")
	public String updateGig(@RequestBody GigRequest gigRequest) {
		Long id = gigRequest.getCustomerId();
		Customer customer = customerDao.findById(id).get();
		
		Gig gig = new Gig();
		gig.setProjectImage(gigRequest.getProjectImage());
		gig.setProjectName(gigRequest.getProjectName());
		gig.setProjectIndustry(gigRequest.getProjectIndustry());
		gig.setProjectType(gigRequest.getProjectType());
		gig.setDuration(gigRequest.getDuration());
		gig.setQuotationPrice(gigRequest.getQuotationPrice());
		gig.setDescription(gigRequest.getDescription());
		gig.setSkillsRequired(gigRequest.getSkillsRequired());
		gig.setGig_status(GIG_STATUS.ACTIVE);
		
		customer.setCurrent_gig(gig);
		customerDao.save(customer);
		return "Customer Gig Updated";
	}
	
	@PostMapping("/customer/updategig/status")
	public String updateGigStatus(@RequestBody GigRequest gigRequest) {
		
		Long id = gigRequest.getCustomerId();
		Customer customer = customerDao.findById(id).get();
		Gig gig = customer.getCurrent_gig();
		gig.setGig_status(GIG_STATUS.valueOf(gigRequest.getGigStatus()));
		
		customer.setCurrent_gig(gig);
		customerDao.save(customer);
		return "Customer Gig Updated";
	}
	
	@GetMapping("/customer/gig/skills")
	public List<String> getGigSkills(@RequestParam Long customerId) {
		Customer customer = customerDao.findById(customerId).get();
		return customer.getCurrent_gig().getSkillsRequired();
	}
	
	@PostMapping("/customer/allot-gig")
	public void allotGig(@RequestBody Map<String, Long> request) {
		Long customerId = request.get("customer_id");
		Long candidateId = request.get("candidate_id");
		Candidate candidate = candidateDao.findById(candidateId).get();
		Customer customer = customerDao.findById(customerId).get();
		Gig gig = customer.getCurrent_gig();
		gig.setGig_status(GIG_STATUS.INACTIVE);
		candidate.setTotalSessions(candidate.getTotalSessions() + 1);
		candidateDao.save(candidate);
		customerDao.save(customer);
	}
	
	@GetMapping("/customer/gig/candidates")
	public List<Candidate> getCandidates(@RequestParam Long customerId){
		Customer customer = customerDao.findById(customerId).get();
		Gig gig = customer.getCurrent_gig();
		String description = gig.getDescription();
		String industry = gig.getProjectIndustry();
		String type = gig.getProjectType();
		if(ObjectUtils.isEmpty(description)) {
			description = "";
		}
		if(ObjectUtils.isEmpty(industry)) {
			industry = "";
		}
		if(ObjectUtils.isEmpty(type)) {
			type = "";
		}
		List<String> tokenDescription = new ArrayList(Arrays.asList(description.split("\\s+")));
		tokenDescription.add(industry);
		tokenDescription.add(type);
		Map<String, Boolean> ats = new HashMap<String, Boolean>();
		for(String token : tokenDescription) {
			ats.put(token, true);
		}
		Iterable<Candidate> cd = (candidateDao.findAll());
		List<Candidate> candidates = new ArrayList<>();
		List<CustomPair> finalResult = new ArrayList<>();
		cd.forEach(candidates::add);
	    for(Candidate curr : candidates) {
	    	Integer matchingCount = 0;
	    	for(String skill : curr.getPreSkills()) {
	    		if(ats.containsKey(skill)) {
	    			matchingCount++;
	    		}
	    	}
	    	CustomPair cp = new CustomPair();
	    	cp.setCandidate(curr);
	    	cp.setMatch(matchingCount);
	    	finalResult.add(cp);
	    }
	    finalResult.sort((o1, o2) -> o1.getMatch().compareTo(o2.getMatch()));
	    Integer rateLimiter = 0;
	    List<Candidate> cutoff = new ArrayList<>();
	    for(CustomPair curr : finalResult) {
	    	if(rateLimiter == 5) {
	    		break;
	    	}
	    	cutoff.add(curr.getCandidate());
	    	rateLimiter += 1;
	    }
	    return cutoff;
	}
}
