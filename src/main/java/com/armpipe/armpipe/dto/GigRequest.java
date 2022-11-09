package com.armpipe.armpipe.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GigRequest {
	@JsonProperty("customer_id")
	Long customerId;
	
	@JsonProperty("project_name")
	String projectName;
	
	@JsonProperty("project_industry")
	String projectIndustry;
	
	@JsonProperty("project_type")
	String projectType;
	
	@JsonProperty("duration")
	Long duration;
	
	@JsonProperty("description")
	String description;
	
	@JsonProperty("quotation_price")
	Double quotationPrice;
	
	@JsonProperty("project_image")
	String projectImage;
	
	@JsonProperty("gig_status")
	String gigStatus;
	
	@JsonProperty("skills_required")
	List<String> skillsRequired;
}
