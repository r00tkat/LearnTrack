package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;
import java.util.List;

public class EnrollmentService {

    private final EnrollmentRepository repository = new EnrollmentRepository();

    // Enroll a student in a course. We verify both exist first.
    public Enrollment enroll(StudentService studentService, CourseService courseService,
                             int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);  // throws if missing
        Course course = courseService.findCourseById(courseId);       // throws if missing

        int id = IdGenerator.getNextEnrollmentId();
        Enrollment enrollment = new Enrollment(id, student.getId(), course.getId());
        repository.save(enrollment);
        return enrollment;
    }

    public List<Enrollment> getEnrollmentsForStudent(int studentId) {
        return repository.findByStudentId(studentId);
    }

    public void completeEnrollment(int enrollmentId) {
        Enrollment e = findEnrollmentById(enrollmentId);
        e.setStatus(EnrollmentStatus.COMPLETED);
    }

    public void cancelEnrollment(int enrollmentId) {
        Enrollment e = findEnrollmentById(enrollmentId);
        e.setStatus(EnrollmentStatus.CANCELLED);
    }

    private Enrollment findEnrollmentById(int id) {
        Enrollment e = repository.findById(id);
        if (e == null) {
            throw new EntityNotFoundException("Enrollment with id " + id + " not found.");
        }
        return e;
    }
}