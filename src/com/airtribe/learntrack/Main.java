package com.airtribe.learntrack;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.exception.InvalidInputException;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import java.util.List;
import java.util.Scanner;

// The console UI. Only job: show menus, read input, call services.
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final StudentService studentService = new StudentService();
    private static final CourseService courseService = new CourseService();
    private static final EnrollmentService enrollmentService = new EnrollmentService();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter choice: ");
            switch (choice) {
                case 1 -> studentMenu();
                case 2 -> courseMenu();
                case 3 -> enrollmentMenu();
                case 0 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("\n===== LearnTrack =====");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    // ---------------- STUDENT ----------------
    private static void studentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Student Management ---");
            System.out.println("1. Add student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate student");
            System.out.println("0. Back");
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addStudent();
                    case 2 -> viewStudents();
                    case 3 -> searchStudent();
                    case 4 -> deactivateStudent();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void addStudent() {
        String first = readLine("First name: ");
        String last = readLine("Last name: ");
        String email = readLine("Email (leave blank if none): ");
        String batch = readLine("Batch: ");
        Student s;
        if (email == null || email.trim().isEmpty()) {
            s = studentService.addStudent(first, last, batch);          // no-email overload
        } else {
            s = studentService.addStudent(first, last, email, batch);   // with-email overload
        }
        System.out.println("Added student with ID: " + s.getId());
    }

    private static void viewStudents() {
        List<Student> students = studentService.listStudents();
        if (students.isEmpty()) {
            System.out.println("No students yet.");
            return;
        }
        for (Student s : students) {
            System.out.println(s.getId() + " | " + s.getDisplayName()
                    + " | batch=" + s.getBatch() + " | active=" + s.isActive());
        }
    }

    private static void searchStudent() {
        int id = readInt("Enter student ID: ");
        Student s = studentService.findStudentById(id);
        System.out.println("Found: " + s.getDisplayName() + " | active=" + s.isActive());
    }

    private static void deactivateStudent() {
        int id = readInt("Enter student ID to deactivate: ");
        studentService.deactivateStudent(id);
        System.out.println("Student " + id + " deactivated.");
    }

    // ---------------- COURSE ----------------
    private static void courseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Course Management ---");
            System.out.println("1. Add course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate/Deactivate course");
            System.out.println("0. Back");
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> addCourse();
                    case 2 -> viewCourses();
                    case 3 -> toggleCourse();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void addCourse() {
        String name = readLine("Course name: ");
        String desc = readLine("Description: ");
        int weeks = readInt("Duration (weeks): ");
        Course c = courseService.addCourse(name, desc, weeks);
        System.out.println("Added course with ID: " + c.getId());
    }

    private static void viewCourses() {
        List<Course> courses = courseService.listCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses yet.");
            return;
        }
        for (Course c : courses) {
            System.out.println(c.getId() + " | " + c.getCourseName()
                    + " | " + c.getDurationInWeeks() + "wks | " + c.getStatus());
        }
    }

    private static void toggleCourse() {
        int id = readInt("Enter course ID: ");
        courseService.toggleCourseStatus(id);
        System.out.println("Course " + id + " status toggled.");
    }

    // ---------------- ENROLLMENT ----------------
    private static void enrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Enrollment Management ---");
            System.out.println("1. Enroll student in course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment completed");
            System.out.println("4. Cancel enrollment");
            System.out.println("0. Back");
            int choice = readInt("Enter choice: ");
            try {
                switch (choice) {
                    case 1 -> enrollStudent();
                    case 2 -> viewEnrollments();
                    case 3 -> completeEnrollment();
                    case 4 -> cancelEnrollment();
                    case 0 -> back = true;
                    default -> System.out.println("Invalid option.");
                }
            } catch (EntityNotFoundException | InvalidInputException ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void enrollStudent() {
        int studentId = readInt("Student ID: ");
        int courseId = readInt("Course ID: ");
        Enrollment e = enrollmentService.enroll(studentService, courseService, studentId, courseId);
        System.out.println("Enrolled. Enrollment ID: " + e.getId());
    }

    private static void viewEnrollments() {
        int studentId = readInt("Student ID: ");
        List<Enrollment> list = enrollmentService.getEnrollmentsForStudent(studentId);
        if (list.isEmpty()) {
            System.out.println("No enrollments for this student.");
            return;
        }
        for (Enrollment e : list) {
            System.out.println("EnrollID=" + e.getId() + " | courseId=" + e.getCourseId()
                    + " | date=" + e.getEnrollmentDate() + " | " + e.getStatus());
        }
    }

    private static void completeEnrollment() {
        int id = readInt("Enrollment ID: ");
        enrollmentService.completeEnrollment(id);
        System.out.println("Enrollment " + id + " marked COMPLETED.");
    }

    private static void cancelEnrollment() {
        int id = readInt("Enrollment ID: ");
        enrollmentService.cancelEnrollment(id);
        System.out.println("Enrollment " + id + " CANCELLED.");
    }

    // ---------------- INPUT HELPERS ----------------
    // Reads an int safely; re-asks on bad input (exception handling).
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}