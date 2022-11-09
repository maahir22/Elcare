package com.armpipe.armpipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.armpipe.armpipe.dao.CourseDao;
import com.armpipe.armpipe.dto.Course;

@RestController
public class CourseController {

	@Autowired
	private CourseDao courseDao;
	
	@PostMapping("/course/add")
	public void addCourse(@RequestBody Course course) {
		courseDao.save(course);
		
	}
}
