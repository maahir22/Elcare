package com.armpipe.armpipe.dao;

import org.springframework.data.repository.CrudRepository;

import com.armpipe.armpipe.dto.Candidate;
import com.armpipe.armpipe.dto.Course;

public interface CourseDao extends CrudRepository<Course, Long>{

}
