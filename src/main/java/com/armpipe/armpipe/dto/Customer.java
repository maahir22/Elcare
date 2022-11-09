package com.armpipe.armpipe.dto;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.armpipe.armpipe.enums.GIG_STATUS;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
	
	@JsonProperty("customer_name")
	String customerName;
	
	@Id
	@JsonProperty("phone_number")
	Long phoneNumber;
	
	@JsonProperty("zip_code")
	Long zipCode;
	
	@JsonProperty("customer_address")
	String customerAddress;
	
	@JsonProperty("customer_rating")
	Double customerRating;
	
	@JsonProperty("customer_requirements")
	String customerRequirements;
		
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "gig_id", referencedColumnName = "id")
	Gig current_gig;
}
