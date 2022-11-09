package com.armpipe.armpipe.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.armpipe.armpipe.dao.CandidateDao;
import com.armpipe.armpipe.dao.CourseDao;
import com.armpipe.armpipe.dao.CustomerDao;
import com.armpipe.armpipe.dto.Candidate;
import com.armpipe.armpipe.dto.Course;

@RestController
public class DefenseController {
	@Autowired
	private CandidateDao candidateDao;
	
	@Autowired
	private CourseDao courseDao;

	@PostMapping("/candidate/register")
	public String addCandidate(@RequestBody Candidate candidate) {
		candidateDao.save(candidate);
		return "Added candidate";
	}
	
	@GetMapping("/candidate/skills")
	public List<String> getSkills(@RequestParam Long cid){
		Candidate candidate = candidateDao.findById(cid).get();
		return candidate.getPreSkills();
	}
	
	@PostMapping("/candidate/enroll-course")
	public String enrollCourse(@RequestBody Map<String, Long> request) {
		Long cid = request.get("candidate_id");
		Long courseId = request.get("course_id");
		Candidate candidate = candidateDao.findById(cid).get();
		Course course = courseDao.findById(courseId).get();
		candidate.setCourse(course);
		candidateDao.save(candidate);
		return "Enrolled in Course";
	}
	
	@GetMapping("/candidate/course")
	public String getCandidateCourse(@RequestParam Long cid){
		Candidate candidate = candidateDao.findById(cid).get();
		return candidate.getCourse().getCourseName() + " : " + candidate.getCourse().getCourseVideos();
	}
	
	@GetMapping("/candidate/increment-course")
	public String incrementCourse(@RequestParam Long cid){
		Candidate candidate = candidateDao.findById(cid).get();
		Course course = candidate.getCourse();	
		Double status = course.getCompletionStatus();
		if(ObjectUtils.isEmpty(status)) {
			status = 0.00D;
		}
		course.setCompletionStatus(Math.min(status + 10, 100));
		if(course.getCompletionStatus() >= 100) {
			candidate.getPreSkills().addAll(course.getCourseSkills());
			System.out.println(course.getCourseSkills());
			System.out.println(candidate.getPreSkills());
		}
		candidate.setCourse(course);
		courseDao.save(course);
		candidateDao.save(candidate);
		return "Completed Updating Status";
	}
	
	@GetMapping("/candidate/course-percent")
	public Map<String, Double> getCoursePercent(@RequestParam Long cid){
		Candidate candidate = candidateDao.findById(cid).get();
		Course course = candidate.getCourse();
		Map<String, Double> response = new HashMap<>();
		response.put("completion_percent", course.getCompletionStatus());
		return response;
	}
	
	@PostMapping("/candidate/update-rating")
	public void updateRating(@RequestParam Map<String, Long> request) {
		Long cid = request.get("cid");
		Long rating = request.get("rating");
		Candidate candidate = candidateDao.findById(cid).get();
		Double preRating = (candidate.getTotalSessionRatings() + rating);
		Integer totalSession = candidate.getTotalSessions();
		Double finalRating = preRating / totalSession;
		candidate.setFinalRating(finalRating);
		candidateDao.save(candidate);
	}
}
