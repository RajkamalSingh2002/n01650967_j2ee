package com.example.students.service;

import com.example.students.entity.Student;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    private Map<Integer, Student> studentMap = new HashMap<>();

    public StudentServiceImpl() {
        // Pre-populate with 3 default records
        studentMap.put(1, new Student(1, "Alice Johnson", 22, "Female",
                "alice@example.com", "Toronto", LocalDate.of(2003, 5, 14)));
        studentMap.put(2, new Student(2, "Bob Smith", 24, "Male",
                "bob@example.com", "Vancouver", LocalDate.of(2001, 8, 22)));
        studentMap.put(3, new Student(3, "Carol White", 21, "Female",
                "carol@example.com", "Montreal", LocalDate.of(2004, 1, 30)));
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentMap.values());
    }

    @Override
    public void addStudent(Student student) {
        studentMap.put(student.getId(), student);
    }
}