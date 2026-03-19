package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    private final EmployeeImpl employeeImpl;

    public EmployeeController(EmployeeImpl employeeImpl) {
        this.employeeImpl = employeeImpl;
    }

    // Show all employees
    @GetMapping("/")
    public String getAllEmployees(Model model) {
        model.addAttribute("employeeList", employeeImpl.getAllEmployees());
        return "index";
    }

    // Show Add Employee form
    @GetMapping("/showAddEmployeeForm")
    public String showAddEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "addEmployee";
    }

    // Save new employee
    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee) {
        employeeImpl.addEmployee(employee);
        return "redirect:/";
    }

    // Show Update Employee form
    @GetMapping("/showUpdateForm/{id}")
    public String showUpdateForm(@PathVariable(value = "id") int id, Model model) {
        Employee employee = employeeImpl.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "updateEmployee";
    }

    // Save updated employee
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeImpl.updateEmployee(employee);
        return "redirect:/";
    }

    // Delete employee
    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable(value = "id") int id) {
        employeeImpl.deleteEmployee(id);
        return "redirect:/";
    }
}
