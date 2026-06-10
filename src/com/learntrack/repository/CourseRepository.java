package com.learntrack.repository;

import com.learntrack.entity.Course;
import java.util.ArrayList;
import java.util.List;

public class CourseRepository {

    private final List<Course> courses = new ArrayList<>();

    public void save(Course course) {
        courses.add(course);
    }

    public List<Course> findAll() {
        return courses;
    }

    public Course findById(int id) {
        for (Course c : courses) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}