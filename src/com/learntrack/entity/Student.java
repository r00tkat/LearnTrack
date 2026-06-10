package com.learntrack.entity;

// Student IS-A Person, so it extends Person and reuses its fields.
public class Student extends Person {

    // Student-only fields (Person already gives us id, name, email)
    private String batch;
    private boolean active;

    // Default constructor
    public Student() {
        super();              // calls Person's default constructor
    }

    // Parameterized constructor WITH email
    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);  // Person sets id/name/email
        this.batch = batch;
        this.active = true;   // a new student starts active
    }

    // Constructor overloading: a version WITHOUT email
    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, null);   // email left empty
        this.batch = batch;
        this.active = true;
    }

    // Getters and setters for the student-only fields
    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public boolean isActive() {     // boolean getters use "is" by convention
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Override Person's method = polymorphism.
    // Same method name, specialized behaviour for a Student.
    @Override
    public String getDisplayName() {
        return "Student: " + getFirstName() + " " + getLastName();
    }
}