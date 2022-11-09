package com.armpipe.armpipe.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.web.client.RestTemplate;

import com.armpipe.armpipe.enums.VER_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class Candidate {
	@JsonProperty("name")
	String name;
	
	@JsonProperty("address")
	String address;
	
	@JsonProperty("experience_rating")
	Double experienceRating;
	
	@Id
	@JsonProperty("phone_number")
	@Column(name = "phone_number")
	Long phoneNumber;
	
	@JsonProperty("profile_image")
	String profileImage;
	
	VER_STATUS ver_status;
	
	@JsonProperty("gov_id")
	String govID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id", referencedColumnName = "id")
	Course course;
	
	@JsonProperty("pre_skills")
	@ElementCollection
    @CollectionTable(name = "pre_skills", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "previous_skills")
    private List<String> preSkills;
	
	@JsonProperty("total_sessions")
	Integer totalSessions = 0;
	
	Double totalSessionRatings = 0.00D;
	
	Double finalRating;
	
	Boolean verifiedStatus;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}
	public VER_STATUS getVer_status() {
		return ver_status;
	}
	public void setVer_status(VER_STATUS ver_status) throws JsonMappingException, JsonProcessingException {
		//GET Request
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<String> response = restTemplate.postForObject(null, restTemplate, HttpEntity.class);
		String curr = response.getBody();
		ObjectMapper obj = new ObjectMapper();
		restTemplate.getForObject(null, null);
		Candidate candidate = obj.readValue(curr, Candidate.class);
		//Request with headers
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("authorisation", "token");
		Map<String, String> req = new HashMap<>();
		req.put("lmao", "test");
		HttpEntity httpEntity = new HttpEntity(req, httpHeaders);
		HttpEntity resp = restTemplate.postForEntity(null, httpEntity, HttpEntity.class);
		Candidate candidate = resp.getBody();
		this.ver_status = ver_status;
	}
	public String getGovID() {
		return govID;
	}
	public void setGovID(String govID) {
		this.govID = govID;
	}
	
}
