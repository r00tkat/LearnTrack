package com.airtribe.learntrack.util;

import com.airtribe.learntrack.constants.AppConstants;

// Generates unique IDs using STATIC counters shared across the whole app.
public class IdGenerator {

    // static = ONE shared copy, not one per object.
    private static int studentIdCounter = AppConstants.STUDENT_ID_START;
    private static int courseIdCounter = AppConstants.COURSE_ID_START;
    private static int enrollmentIdCounter = AppConstants.ENROLLMENT_ID_START;

    // Private constructor: nobody should create an IdGenerator object.
    private IdGenerator() {
    }

    public static int getNextStudentId() {
        return studentIdCounter++;   // return current value, then increase by 1
    }

    public static int getNextCourseId() {
        return courseIdCounter++;
    }

    public static int getNextEnrollmentId() {
        return enrollmentIdCounter++;
    }
}