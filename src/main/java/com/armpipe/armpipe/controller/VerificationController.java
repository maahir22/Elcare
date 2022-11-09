package com.armpipe.armpipe.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

@RestController
public class VerificationController {
	
	@PostMapping("initiate-verification")
	public String InitiateVerification(Map<String, String> candidateVerify) {
		String govID = candidateVerify.get("government_id");
		Map<String, String> req = new HashMap<>();
		HttpHeaders header = new HttpHeaders();
		HttpEntity<Map<String, String>> entity = new HttpEntity<>(req, header);
		RestTemplate restTemplate = new RestTemplate();
		String hardCoded = restTemplate.postForObject("http://localhost:4444/armyVerifier", entity, String.class);
		return "OK";
	}
	
	@PostMapping("confirm-verification")
	public Boolean confirmVerification(Long phoneNumber, String OTP) {
		return true;
	}
	
}
