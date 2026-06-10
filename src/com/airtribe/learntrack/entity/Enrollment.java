package com.airtribe.learntrack.entity;

import com.airtribe.learntrack.enums.EnrollmentStatus;
import java.time.LocalDate;

// Enrollment links one student to one course.
public class Enrollment {

    private int id;
    private int studentId;              // which student
    private int courseId;               // which course
    private LocalDate enrollmentDate;   // when they enrolled
    private EnrollmentStatus status;    // ACTIVE / COMPLETED / CANCELLED

    // Default constructor
    public Enrollment() {
    }

    // Parameterized constructor
    public Enrollment(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = LocalDate.now();      // today's date automatically
        this.status = EnrollmentStatus.ACTIVE;      // starts ACTIVE
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollmentStatus status) {
        this.status = status;
    }
}