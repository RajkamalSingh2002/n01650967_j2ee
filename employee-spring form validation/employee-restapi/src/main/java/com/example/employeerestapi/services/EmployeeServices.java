package com.example.employeerestapi.services;

import com.example.employeerestapi.models.Employee;

import org.springframework.stereotype.Service;
import java.util.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeServices {

    private static Map<Integer, Employee> empMap = new HashMap<>();
    private static int currentId = 1;

    public List<Employee> getAll() {
        return new ArrayList<>(empMap.values());
    }

    public Employee getById(int id) {
        return empMap.get(id);
    }

    public Employee create(Employee emp) {
        emp.setId(currentId++);
        empMap.put(emp.getId(), emp);
        return emp;
    }

    public Employee update(int id, Employee emp) {
        if (!empMap.containsKey(id)) return null;
        emp.setId(id);
        empMap.put(id, emp);
        return emp;
    }

    public boolean delete(int id) {
        return empMap.remove(id) != null;
    }
}