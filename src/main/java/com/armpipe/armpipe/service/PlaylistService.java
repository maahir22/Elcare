package com.armpipe.armpipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armpipe.armpipe.dao.CandidateDao;
import com.armpipe.armpipe.dao.PlaylistDao;
import com.armpipe.armpipe.dto.Candidate;

@Service
public class PlaylistService {
	@Autowired
	private PlaylistDao playlistDao;
	
}
