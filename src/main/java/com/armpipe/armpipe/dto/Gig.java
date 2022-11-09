package com.armpipe.armpipe.dto;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import com.armpipe.armpipe.enums.GIG_STATUS;

import lombok.Data;

@Data
@Entity
public class Gig {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String projectName;
	String projectIndustry;
	String projectType;
	Long duration;
	Double quotationPrice;
	String description;
	String projectImage;
	GIG_STATUS gig_status;
	
	@ElementCollection // 1
    @CollectionTable(name = "all_skills", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "skills_required")
    private List<String> skillsRequired;
}
