package com.armpipe.armpipe.dto;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Playlist {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	long pid;
	String videoURL;
	long priority;
}
