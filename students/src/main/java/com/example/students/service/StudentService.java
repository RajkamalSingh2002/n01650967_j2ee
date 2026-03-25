package com.example.students.service;

import com.example.students.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void addStudent(Student student);
}