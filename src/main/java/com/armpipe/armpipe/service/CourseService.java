package com.armpipe.armpipe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.armpipe.armpipe.dao.CandidateDao;
import com.armpipe.armpipe.dao.CourseDao;
import com.armpipe.armpipe.dao.PlaylistDao;
import com.armpipe.armpipe.dto.Candidate;
import com.armpipe.armpipe.dto.Course;

@Service
public class CourseService {
	@Autowired
	private CourseDao courseDao;
	
	public void addCourse(Course course) {
		courseDao.save(course);
	}
}
