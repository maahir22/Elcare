package com.armpipe.armpipe.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@JsonProperty("course_Name")
	String courseName;
	
	@JsonProperty("course_keywords")
	String courseKeywords;		//Pipe Separated
	
	@JsonProperty("course_image")
	String courseImage;
	
	@JsonProperty("comments")
	String comments;
	
	@JsonProperty("course_skills")
	@ElementCollection
    @CollectionTable(name = "course_skills", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "course_skill")
	private List<String> courseSkills;
	
	@JsonProperty("module_videos")
	@ElementCollection
    @CollectionTable(name = "all_vidoes", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "module_videos")
    private List<String> courseVideos;
	
	Double completionStatus = 0.00D;
	
}
