package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import com.airtribe.learntrack.util.InputValidator;
import java.util.List;

// Business logic for students. Talks to the repository; Main talks to this.
public class StudentService {

    private final StudentRepository repository = new StudentRepository();

    // Overload 1: add student WITH email
    public Student addStudent(String firstName, String lastName, String email, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, email, batch);
        repository.save(student);
        return student;
    }

    // Overload 2: add student WITHOUT email (method overloading example)
    public Student addStudent(String firstName, String lastName, String batch) {
        InputValidator.requireNonBlank(firstName, "First name");
        InputValidator.requireNonBlank(lastName, "Last name");
        int id = IdGenerator.getNextStudentId();
        Student student = new Student(id, firstName, lastName, batch);
        repository.save(student);
        return student;
    }

    public List<Student> listStudents() {
        return repository.findAll();
    }

    // Find by id, or throw a clean exception if not found.
    public Student findStudentById(int id) {
        Student student = repository.findById(id);
        if (student == null) {
            throw new EntityNotFoundException("Student with id " + id + " not found.");
        }
        return student;
    }

    // Deactivate instead of deleting (soft-delete).
    public void deactivateStudent(int id) {
        Student student = findStudentById(id);
        student.setActive(false);
    }
}