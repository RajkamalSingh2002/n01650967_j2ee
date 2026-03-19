package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAllEmployees();
    void addEmployee(Employee employee);
    Employee getEmployeeById(int id);
    void updateEmployee(Employee employee);
    void deleteEmployee(int id);
}
