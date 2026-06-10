package com.learntrack.service;

import com.learntrack.entity.Course;
import com.learntrack.enums.CourseStatus;
import com.learntrack.exception.EntityNotFoundException;
import com.learntrack.repository.CourseRepository;
import com.learntrack.util.IdGenerator;
import com.learntrack.util.InputValidator;
import java.util.List;

public class CourseService {

    private final CourseRepository repository = new CourseRepository();

    public Course addCourse(String courseName, String description, int durationInWeeks) {
        InputValidator.requireNonBlank(courseName, "Course name");
        InputValidator.requirePositive(durationInWeeks, "Duration");
        int id = IdGenerator.getNextCourseId();
        Course course = new Course(id, courseName, description, durationInWeeks);
        repository.save(course);
        return course;
    }

    public List<Course> listCourses() {
        return repository.findAll();
    }

    public Course findCourseById(int id) {
        Course course = repository.findById(id);
        if (course == null) {
            throw new EntityNotFoundException("Course with id " + id + " not found.");
        }
        return course;
    }

    // Flip a course between ACTIVE and INACTIVE.
    public void toggleCourseStatus(int id) {
        Course course = findCourseById(id);
        if (course.getStatus() == CourseStatus.ACTIVE) {
            course.setStatus(CourseStatus.INACTIVE);
        } else {
            course.setStatus(CourseStatus.ACTIVE);
        }
    }
}