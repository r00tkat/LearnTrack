package com.learntrack.entity;

public class Person {

    // Private fields = encapsulation (only reachable via getters/setters)
    private int id;
    private String firstName;
    private String lastName;
    private String email;

    // Default (no-argument) constructor
    public Person() {
    }

    // Parameterized constructor (constructor overloading: two versions exist)
    public Person(int id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Method that child classes will override later (polymorphism)
    public String getDisplayName() {
        return firstName + " " + lastName;
    }
}