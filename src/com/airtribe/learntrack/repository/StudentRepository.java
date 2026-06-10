package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;
import java.util.ArrayList;
import java.util.List;

// Stores Student objects in memory using an ArrayList.
public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public List<Student> findAll() {
        return students;
    }

    // Returns the matching student, or null if none found.
    public Student findById(int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        return null;
    }
}