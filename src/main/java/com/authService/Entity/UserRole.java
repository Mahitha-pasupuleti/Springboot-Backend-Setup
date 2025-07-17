package com.authService.Entity;

public enum UserRole {
    STUDENT,             // Registers, enrolls in courses, views grades
    PROFESSOR,           // Manages course content, assigns grades
    TEACHING_ASSISTANT,
    ADMIN,               // Manages users, departments, system configs, // Approves enrollment, manages student records
    PRESIDENT,      // Manages department-level courses, professors
    VICE_PRESIDENT,
    DEAN
}
